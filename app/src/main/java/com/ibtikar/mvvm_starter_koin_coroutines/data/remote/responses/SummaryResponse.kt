package com.ibtikar.mvvm_starter_koin_coroutines.data.remote.responses

import com.google.gson.annotations.SerializedName
import com.ibtikar.mvvm_starter_koin_coroutines.data.remote.entities.CountryNumbersSummary
import com.ibtikar.mvvm_starter_koin_coroutines.data.remote.entities.GlobalNumbersSummary

data class SummaryResponse(
    @SerializedName("Global") val globalNumbersSummary: GlobalNumbersSummary? = null,
    @SerializedName("Countries") val countriesNumbersSummary: ArrayList<CountryNumbersSummary>? = null
)