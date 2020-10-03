package com.ibtikar.mvvm_starter_koin_coroutines.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibtikar.mvvm_starter_koin_coroutines.ApplicationRunTimeException
import com.ibtikar.mvvm_starter_koin_coroutines.utils.coroutines.ContextProviders
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseViewModel(private val contextProvider: ContextProviders) : ViewModel() {

    internal val internalState = MutableLiveData<ViewState>()

    val state: LiveData<ViewState> = internalState

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        internalState.value = ViewState.Error(exception as ApplicationRunTimeException)
    }

    fun launchSuspendingBlock(
        displayLoader: Boolean = true,
        block: suspend CoroutineScope.() -> Unit
    ) {
        internalState.value = ViewState.Loading(displayLoader)
        viewModelScope.launch(contextProvider.Main + coroutineExceptionHandler) {
            block.invoke(this)
        }
    }

}