package com.ibtikar.mvvm_starter_koin_coroutines.data.remote.apis

import com.ibtikar.mvvm_starter_koin_coroutines.data.remote.responses.SummaryResponse
import retrofit2.Response
import retrofit2.http.GET

interface Covid19Api {

    @GET(SUMMARY)
    suspend fun getSummary(): Response<SummaryResponse>
    companion object {
        const val SUMMARY = "summary"
    }
}
