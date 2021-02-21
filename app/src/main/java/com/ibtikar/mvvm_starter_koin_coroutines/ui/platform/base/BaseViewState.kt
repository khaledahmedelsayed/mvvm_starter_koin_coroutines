package com.ibtikar.mvvm_starter_koin_coroutines.ui.platform.base

import com.ibtikar.mvvm_starter_koin_coroutines.common.ApplicationRunTimeException

open class BaseViewState {
    data class Loading(val displayLoading: Boolean = true) : BaseViewState()
    data class Error(val applicationRunTimeException: ApplicationRunTimeException) : BaseViewState()
    // Loaded Interface To make it an addition to any ViewState, and thus it can be handled solely in the overridden onLoaded() method in parent and child view
    interface Loaded {
        companion object {
            val instance = object : BaseViewState() { }
        }
    }
}
