package com.khaledahmedelsayed.mvvmwithstatestarter.ui.platform.base

import com.khaledahmedelsayed.mvvmwithstatestarter.common.ApplicationRunTimeException

open class BaseViewState {
    data class Loading(val displayLoading: Boolean = true) : BaseViewState()
    data class Error(val applicationRunTimeException: ApplicationRunTimeException) : BaseViewState()
    interface Loaded {
        companion object {
            val instance = object : BaseViewState() { }
        }
    }
}
