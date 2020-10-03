package com.ibtikar.mvvm_starter_koin_coroutines.ui.home

import com.ibtikar.mvvm_starter_koin_coroutines.ui.base.BaseViewModel
import com.ibtikar.mvvm_starter_koin_coroutines.utils.coroutines.ContextProviders

class HomeViewModel(
    private val homeRepository: HomeRepository,
    contextProviders: ContextProviders
) : BaseViewModel(contextProviders) {

    fun loadCovid19Summary() {
        launchSuspendingBlock {
            val loadedData = homeRepository.getCovid19Summary()
            internalState.value = HomeViewState.OnCovidSummaryRetrieved(loadedData)
        }
    }
}