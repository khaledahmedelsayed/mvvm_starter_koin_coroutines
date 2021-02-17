package com.ibtikar.mvvm_starter_koin_coroutines.ui.features.home

import com.ibtikar.mvvm_starter_koin_coroutines.data.remote.responses.SummaryResponse
import com.ibtikar.mvvm_starter_koin_coroutines.ui.platform.base.BaseViewState

sealed class HomeViewState : BaseViewState() {
    data class OnCovidSummaryRetrieved(val summary: SummaryResponse?) : HomeViewState(), Loaded
}
