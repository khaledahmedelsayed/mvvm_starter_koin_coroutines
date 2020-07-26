package com.ibtikar.mvvm_starter_koin_coroutines.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibtikar.mvvm_starter_koin_coroutines.utils.coroutines.ContextProviders
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseViewModel(private val contextProvider: ContextProviders) : ViewModel() {

    internal val internalState = MutableLiveData<ViewState>()

    val state: LiveData<ViewState> = internalState

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        internalState.value = ViewState.Error(throwable.message)
    }

    fun launchBlock(block: suspend CoroutineScope.() -> Unit) {
        internalState.value = ViewState.Loading
        viewModelScope.launch(contextProvider.Main + coroutineExceptionHandler) {
            block.invoke(this)
        }
    }
}