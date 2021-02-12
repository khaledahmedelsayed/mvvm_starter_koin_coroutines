package com.ibtikar.mvvm_starter_koin_coroutines.data.repositories

import com.ibtikar.mvvm_starter_koin_coroutines.common.helpers.ContextProviders
import com.ibtikar.mvvm_starter_koin_coroutines.data.remote.apis.Covid19Api

class HomeRepository(private val covid19Api: Covid19Api, contextProviders: ContextProviders) :
    BaseRepository(contextProviders) {
    suspend fun getCovid19Summary() = executeNetworkCall { covid19Api.getSummary() }
}

