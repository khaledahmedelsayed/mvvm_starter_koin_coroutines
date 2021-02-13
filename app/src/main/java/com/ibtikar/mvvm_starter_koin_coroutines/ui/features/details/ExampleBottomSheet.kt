package com.ibtikar.mvvm_starter_koin_coroutines.ui.features.details

import com.ibtikar.mvvm_starter_koin_coroutines.common.Constants.CORNER_RADIUS
import com.ibtikar.mvvm_starter_koin_coroutines.common.Constants.PEEK_HEIGHT
import com.ibtikar.mvvm_starter_koin_coroutines.databinding.BottomSheetExampleBinding
import com.ibtikar.mvvm_starter_koin_coroutines.ui.platform.base.BaseViewModel
import com.ibtikar.mvvm_starter_koin_coroutines.ui.platform.base.BaseViewState
import com.ibtikar.mvvm_starter_koin_coroutines.ui.platform.bottomsheet.BaseBottomSheetFragment

class ExampleBottomSheet : BaseBottomSheetFragment<BottomSheetExampleBinding, BaseViewModel, BaseViewState>() {
    override val cornerRadius = CORNER_RADIUS
    override val peekHeight = PEEK_HEIGHT

    fun displayDialog() {
        openFragment(ExampleDialog::class.java.newInstance())
    }
}
