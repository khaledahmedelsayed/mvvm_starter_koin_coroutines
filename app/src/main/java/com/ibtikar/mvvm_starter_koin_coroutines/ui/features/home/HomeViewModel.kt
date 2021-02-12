package com.ibtikar.mvvm_starter_koin_coroutines.ui.features.home

import com.ibtikar.mvvm_starter_koin_coroutines.common.helpers.ContextProviders
import com.ibtikar.mvvm_starter_koin_coroutines.data.repositories.HomeRepository
import com.ibtikar.mvvm_starter_koin_coroutines.ui.home.HomeViewState
import com.ibtikar.mvvm_starter_koin_coroutines.ui.platform.base.BaseViewModel

class HomeViewModel(
    private val homeRepository: HomeRepository,
    contextProviders: ContextProviders
) : BaseViewModel(contextProviders) {

    fun loadCovid19Summary() {
        launchSuspendingBlock {
            val response = homeRepository.getCovid19Summary()
            internalState.value = HomeViewState.OnCovidSummaryRetrieved(response.data)
        }
    }
}