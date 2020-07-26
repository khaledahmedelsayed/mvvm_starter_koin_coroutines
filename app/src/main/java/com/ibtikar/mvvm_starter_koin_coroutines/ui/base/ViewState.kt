package com.ibtikar.mvvm_starter_koin_coroutines.ui.base

/**
 * Created by Mohammed Hemdan on 2019-11-04.
 * Email : mohammed.hemdan.faraj@gmail.com
 * Github : https://github.com/mhemdan
 */
abstract class ViewState {
    object Loading : ViewState()
    data class Error(val error: String?) : ViewState()
    abstract class Loaded<out T>(val result: T) : ViewState()
    object Empty : ViewState()
}