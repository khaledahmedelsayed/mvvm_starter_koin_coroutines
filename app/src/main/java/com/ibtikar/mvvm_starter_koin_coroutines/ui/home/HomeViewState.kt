package com.ibtikar.mvvm_starter_koin_coroutines.ui.home

import com.ibtikar.mvvm_starter_koin_coroutines.data.entities.summary.SummaryResponse
import com.ibtikar.mvvm_starter_koin_coroutines.ui.base.ViewState

sealed class HomeViewState : ViewState() {
    data class OnCovidSummaryRetrieved(val loadedData: SummaryResponse?) :
        ViewState.Loaded<SummaryResponse?>(loadedData)
}