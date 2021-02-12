package com.ibtikar.mvvm_starter_koin_coroutines.data.remote.entities

import com.google.gson.annotations.SerializedName

data class GlobalNumbersSummary(
    @SerializedName("NewConfirmed") val newConfirmed: Int = 0,
    @SerializedName("NewDeaths") val newDeaths: Int = 0,
    @SerializedName("NewRecovered") val newRecovered: Int = 0,
    @SerializedName("TotalConfirmed") val totalConfirmed: Int = 0,
    @SerializedName("TotalDeaths") val totalDeaths: Int = 0,
    @SerializedName("TotalRecovered") val totalRecovered: Int = 0
)