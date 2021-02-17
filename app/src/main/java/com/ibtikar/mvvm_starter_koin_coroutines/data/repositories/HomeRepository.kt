package com.ibtikar.mvvm_starter_koin_coroutines.data.repositories

import com.ibtikar.mvvm_starter_koin_coroutines.common.helpers.ContextProviders
import com.ibtikar.mvvm_starter_koin_coroutines.data.remote.apis.Covid19Api
import com.ibtikar.mvvm_starter_koin_coroutines.data.remote.responses.base.ApiBaseResponse

class HomeRepository(private val covid19Api: Covid19Api, contextProviders: ContextProviders) :
    BaseRepository(contextProviders) {
    // Just wrapping the result in ApiBaseResponse in this case (Public API i.e. Covid19Api) ,
    // but usually Backend uses a DTO as response wrapper (i.e. it self is the response containing data, message and errors)
    suspend fun getCovid19Summary() =
        ApiBaseResponse(data = executeNetworkCall { covid19Api.getSummary() })
}
