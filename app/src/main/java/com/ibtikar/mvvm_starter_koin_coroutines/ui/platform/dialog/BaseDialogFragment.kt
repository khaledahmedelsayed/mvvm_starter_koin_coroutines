package com.ibtikar.mvvm_starter_koin_coroutines.ui.platform.dialog

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.databinding.ViewDataBinding
import com.ibtikar.mvvm_starter_koin_coroutines.ui.platform.base.BaseFragment
import com.ibtikar.mvvm_starter_koin_coroutines.ui.platform.base.BaseViewModel
import com.ibtikar.mvvm_starter_koin_coroutines.ui.platform.base.BaseViewState

abstract class BaseDialogFragment<VDB : ViewDataBinding, VM : BaseViewModel, VS : BaseViewState> :
    BaseFragment<VDB, VM, VS>() {

    private var alertDialog: AlertDialog? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val dialogBuilder =
            AlertDialog.Builder(requireContext())

        dialogBuilder.setView(binding.root)

        alertDialog = dialogBuilder.show()

        super.onViewCreated(view, savedInstanceState)
    }

    override fun setupScreenDecorations() {
        super.setupScreenDecorations()
        requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

    override fun onDestroyView() {
        alertDialog = null
        super.onDestroyView()
    }

    override fun onLoading() {
        alertDialog?.hide()
    }

    override fun onError() {
        alertDialog?.hide()
    }

    override fun onLoaded() {
        dismiss()
    }

    fun dismiss() {
        alertDialog?.dismiss()
        closeFragment(this)
    }
}
