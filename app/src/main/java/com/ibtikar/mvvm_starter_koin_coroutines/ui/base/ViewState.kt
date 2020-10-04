package com.ibtikar.mvvm_starter_koin_coroutines.ui.base

/**
 * Created by Mohammed Hemdan on 2019-11-04.
 * Email : mohammed.hemdan.faraj@gmail.com
 * Github : https://github.com/mhemdan
 */
abstract class ViewState {
    data class Loading(val displayLoading: Boolean = true) : ViewState()
    data class Error(val applicationRunTimeException: com.ibtikar.mvvm_starter_koin_coroutines.ApplicationRunTimeException) :
        ViewState()

    abstract class Loaded<out T>(val result: T) : ViewState()
    object Empty : ViewState()
}