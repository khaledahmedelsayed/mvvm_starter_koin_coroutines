package com.ibtikar.mvvm_starter_koin_coroutines.ui.platform.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.activity.OnBackPressedCallback
import androidx.annotation.IdRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ibtikar.mvvm_starter_koin_coroutines.BR
import com.ibtikar.mvvm_starter_koin_coroutines.R
import com.ibtikar.mvvm_starter_koin_coroutines.common.ApplicationRunTimeException
import com.ibtikar.mvvm_starter_koin_coroutines.common.extensions.getGenericTypeClass
import com.ibtikar.mvvm_starter_koin_coroutines.common.extensions.getGenericTypeKClass
import com.ibtikar.mvvm_starter_koin_coroutines.common.types.ErrorType
import com.ibtikar.mvvm_starter_koin_coroutines.ui.ContainerActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

@Suppress("TooManyFunctions")
abstract class BaseFragment<VDB : ViewDataBinding, VM : BaseViewModel, VS : BaseViewState> :
    Fragment() {

    private var _binding: VDB? = null

    // This property is only valid between onCreateView and onDestroyView.
    protected val binding get() = _binding!!
    protected val viewModel by viewModel(getViewModelClass<VM>())

    // Helper vars to persist view
    protected var hasInitializedRootView = false
    private var rootView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = inflateLayoutFromBindingClass(inflater)
        return binding.root
    }

    // Get saved View without recreating; as navigation component doesn't support saving fragments
    // https://stackoverflow.com/a/58835886/11276817
    protected fun getPersistentView(
        inflater: LayoutInflater,
    ): View? {
        var firstRender = true
        if (rootView == null) {
            _binding = inflateLayoutFromBindingClass(inflater)
            rootView = binding.root
        } else {
            (rootView?.parent as? ViewGroup)?.removeView(rootView)
            // Re-observe on state because onViewCreated() will not be called thus liveData observation is lost
            viewModel.baseViewState.observe(
                viewLifecycleOwner
            ) { // Workaround to avoid rendering previous observations from the last time such as errors
                if (firstRender)
                    firstRender = false
                else
                    baseRender(it)
            }
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // To bind a created dataBinding xml variable with the name "viewModel" with the object of VM
        // instead of calling "binding.viewModel = viewModel" in each child fragment. https://stackoverflow.com/a/56611601/11276817
        binding.setVariable(BR.viewModel, viewModel)
        // same for fragment instead of "binding.fragment = this"
        binding.setVariable(BR.fragment, this)
        // To be able to get notified of liveData changes that are bound in XML
        binding.lifecycleOwner = this

        viewModel.baseViewState.observe(viewLifecycleOwner) { baseRender(it) }

        addOnBackPressedCallBack()
        setupScreenDecorations()
        setup()
    }

    private fun addOnBackPressedCallBack() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {

                override fun handleOnBackPressed() {
                    this.remove() // Remove callback to avoid infinite loop
                    onBackPressed { addOnBackPressedCallBack() } // add the same callback as a parameter
                    // to make child fragments be able to re-add the callBack if needed
                }
            }
        )
    }

    override fun onDestroyView() {
        if (rootView == null) // -> Non-PersistentView -> don't keep binding
            _binding = null
        // else -> PersistentView -> binding is saved (not nullified)
        super.onDestroyView()
    }

    private fun baseRender(state: BaseViewState) {
        if (state !is BaseViewState.Loading)
            hideLoading()

        when (state) {
            is BaseViewState.Loading -> {
                if (state.displayLoading)
                    displayLoading()

                onLoading()
            }

            is BaseViewState.Error -> {
                handleError(state.applicationRunTimeException)
                onError()
            }

            else -> {
                @Suppress("UNCHECKED_CAST") // Safe to cast, as VS is always inherited from BaseViewState
                render(state as VS)

                if (state is BaseViewState.Loaded)
                    onLoaded()
            }
        }
    }

    private fun handleError(caughtErrorState: ApplicationRunTimeException) {
        when (caughtErrorState.errorType) {
            ErrorType.Network.ClientSide.NoInternetConnection ->
                caughtErrorState.errorMessage = getString(R.string.internet_connection_error)

            else -> {
                // Ignore
            }
        }
        displayErrorMessage(caughtErrorState.getDisplayError())
    }

    open fun render(screenViewState: VS) {}

    open fun setupScreenDecorations() {
        with(requireActivity().window) {
            decorView.systemUiVisibility = 0
            setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        }
    }

    open fun setup() {}

    open fun onLoading() {}

    open fun onLoaded() {}

    open fun onError() {}

    open fun onBackPressed(addOnBackPressCallBack: () -> Unit) {
        requireActivity().onBackPressed() // Normal onBackPress
    }

    private fun displayLoading() {
        (requireActivity() as ContainerActivity).binding.loadingView.start()
        // Todo
    }

    private fun hideLoading() {
        (requireActivity() as ContainerActivity).binding.loadingView.stop()
        // Todo
    }

    open fun displayErrorMessage(
        message: String?
    ) {
        // Todo
    }

    open fun displaySucceedMessage(message: String?, durationInMs: Long) {
        // Todo
    }

    fun navigateTo(@IdRes resId: Int) {
        findNavController().navigate(resId)
    }

    fun goBack() {
        findNavController().navigateUp()
    }

    fun openFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction().add(fragment, tag).commit()
    }

    fun closeFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction().remove(fragment).commit()
    }

    // Reflect helper methods to avoid boilerplate in each child fragment
    @Suppress("UNCHECKED_CAST")
    private fun <BindingClass> inflateLayoutFromBindingClass(inflater: LayoutInflater) =
        getGenericTypeClass<BindingClass>(0).let {
            it.getMethod("inflate", LayoutInflater::class.java).invoke(it, inflater)
        } as BindingClass

    private fun <ViewModel : Any> getViewModelClass() = getGenericTypeKClass<ViewModel>(1)
}
