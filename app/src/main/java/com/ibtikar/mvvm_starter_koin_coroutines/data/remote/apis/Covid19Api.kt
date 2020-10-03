package com.ibtikar.mvvm_starter_koin_coroutines.data.remote.apis

import com.ibtikar.mvvm_starter_koin_coroutines.data.entities.summary.SummaryResponse
import com.ibtikar.mvvm_starter_koin_coroutines.data.remote.apis.EndPoints.SUMMARY
import retrofit2.Response
import retrofit2.http.GET

interface Covid19Api {

    @GET(SUMMARY)
    suspend fun getSummary(): Response<SummaryResponse>
}
