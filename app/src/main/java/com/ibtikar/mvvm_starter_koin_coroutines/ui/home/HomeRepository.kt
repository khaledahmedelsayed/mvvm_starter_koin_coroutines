package com.ibtikar.mvvm_starter_koin_coroutines.ui.home

import com.ibtikar.mvvm_starter_koin_coroutines.data.remote.apis.Covid19Api
import com.ibtikar.mvvm_starter_koin_coroutines.ui.base.BaseRepository
import com.ibtikar.mvvm_starter_koin_coroutines.utils.coroutines.ContextProviders

class HomeRepository(private val covid19Api: Covid19Api, contextProviders: ContextProviders) :
    BaseRepository(contextProviders) {
//    val gson : Gson by inject() , implement KoinComponent Interface to be able to inject

    suspend fun getCovid19Summary() = suspendingApiCall { covid19Api.getSummary() }
}

