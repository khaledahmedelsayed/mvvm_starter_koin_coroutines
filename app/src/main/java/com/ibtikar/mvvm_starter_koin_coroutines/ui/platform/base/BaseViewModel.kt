package com.ibtikar.mvvm_starter_koin_coroutines.ui.platform.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibtikar.mvvm_starter_koin_coroutines.common.Utils.getCoroutineExceptionHandler
import com.ibtikar.mvvm_starter_koin_coroutines.common.helpers.ContextProviders
import com.ibtikar.mvvm_starter_koin_coroutines.data.repositories.BaseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

open class BaseViewModel(private val contextProvider: ContextProviders) :
    ViewModel(),
    KoinComponent {

    private val baseRepository: BaseRepository by inject()

    internal val internalState = MutableLiveData<BaseViewState>()

    val baseViewState: LiveData<BaseViewState> = internalState

    fun launchSuspendingBlock(
        displayLoader: Boolean = true,
        block: suspend CoroutineScope.() -> Unit
    ) {
        internalState.value = BaseViewState.Loading(displayLoader)
        viewModelScope.launch(contextProvider.main + getCoroutineExceptionHandler(internalState)) {
            block.invoke(this)
        }
    }
}
