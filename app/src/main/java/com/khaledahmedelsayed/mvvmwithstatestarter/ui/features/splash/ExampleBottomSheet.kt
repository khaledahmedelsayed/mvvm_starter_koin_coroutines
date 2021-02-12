package com.khaledahmedelsayed.mvvmwithstatestarter.ui.features.splash

import com.khaledahmedelsayed.mvvmwithstatestarter.common.Constants.CORNER_RADIUS
import com.khaledahmedelsayed.mvvmwithstatestarter.common.Constants.PEEK_HEIGHT
import com.khaledahmedelsayed.mvvmwithstatestarter.databinding.BottomSheetExampleBinding
import com.khaledahmedelsayed.mvvmwithstatestarter.ui.platform.base.BaseViewModel
import com.khaledahmedelsayed.mvvmwithstatestarter.ui.platform.base.BaseViewState
import com.khaledahmedelsayed.mvvmwithstatestarter.ui.platform.bottomsheet.BaseBottomSheetFragment

class ExampleBottomSheet : BaseBottomSheetFragment<BottomSheetExampleBinding, BaseViewModel, BaseViewState>() {
    override val cornerRadius = CORNER_RADIUS
    override val peekHeight = PEEK_HEIGHT

    fun displayDialog() {
        openFragment(ExampleDialog::class.java.newInstance())
    }
}
