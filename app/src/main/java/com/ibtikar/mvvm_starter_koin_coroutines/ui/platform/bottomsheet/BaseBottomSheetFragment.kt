package com.ibtikar.mvvm_starter_koin_coroutines.ui.platform.bottomsheet

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.andrefrsousa.superbottomsheet.SuperBottomSheetFragment
import com.ibtikar.mvvm_starter_koin_coroutines.ui.platform.base.BaseFragment
import com.ibtikar.mvvm_starter_koin_coroutines.ui.platform.base.BaseViewModel
import com.ibtikar.mvvm_starter_koin_coroutines.ui.platform.base.BaseViewState

abstract class BaseBottomSheetFragment<VDB : ViewDataBinding, VM : BaseViewModel, VS : BaseViewState> :
    BaseFragment<VDB, VM, VS>() {

    abstract val cornerRadius: Float
    abstract val peekHeight: Int

    private var bottomSheetFragment: SuperBottomSheetFragment? = null

    override fun setupScreenDecorations() {
        super.setupScreenDecorations()
        requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bottomSheetFragment = SuperBottomSheetFactory.newInstance(
            binding.root,
            cornerRadius,
            peekHeight,
            onDismissListener = {
                bottomSheetFragment = null
                closeFragment(this@BaseBottomSheetFragment)
            }
        )

        openFragment(bottomSheetFragment!!)
        super.onViewCreated(view, savedInstanceState)
    }

    fun dismiss() {
        bottomSheetFragment?.dismiss()
    }
}
