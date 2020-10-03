package com.ibtikar.mvvm_starter_koin_coroutines.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.github.loadingview.LoadingDialog
import com.ibtikar.mvvm_starter_koin_coroutines.ApplicationRunTimeException

abstract class BaseFragment<T : ViewDataBinding, VM : BaseViewModel> : Fragment() {

    protected abstract val viewModel: VM

    protected lateinit var binding: T

    private lateinit var loadingDialog: LoadingDialog

    abstract fun getLayoutId(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, getLayoutId(), container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            loadingDialog = LoadingDialog.get(it)
        }

        setup()
        viewModel.state.observe(viewLifecycleOwner, Observer { baseRender(it) })
    }

    abstract fun setup()

    private fun baseRender(state: ViewState) {
        when (state) {
            is ViewState.Loading -> showLoading(state.displayLoading)
            is ViewState.Error -> showError(state.applicationRunTimeException)
            else -> {
                hideLoading()
                render(state)
            }
        }
    }

    abstract fun render(state: ViewState)

    // not private for the sake of overriding in case of custom implementation for specific screens
    fun showLoading(displayLoading: Boolean) {
        if (displayLoading)
            loadingDialog.show()
    }

    // not private for the sake of overriding in case of custom implementation for specific screens
    fun hideLoading() {
        loadingDialog.hide()
    }

    fun showError(applicationRunTimeExceptionModel: ApplicationRunTimeException?) {
        hideLoading()
        Toast.makeText(context, applicationRunTimeExceptionModel?.errorMessage, Toast.LENGTH_SHORT)
            .show()
    }
}