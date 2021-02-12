package com.ibtikar.mvvm_starter_koin_coroutines.data.remote.apis

import com.ibtikar.mvvm_starter_koin_coroutines.data.remote.responses.SummaryResponse
import com.ibtikar.mvvm_starter_koin_coroutines.data.remote.responses.base.ApiBaseResponse
import retrofit2.Response
import retrofit2.http.GET

interface Covid19Api {

    @GET(SUMMARY)
    suspend fun getSummary(): Response<ApiBaseResponse<SummaryResponse>>
    companion object {
        const val SUMMARY = "summary"
    }
}
