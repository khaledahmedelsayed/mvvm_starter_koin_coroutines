package com.ibtikar.mvvm_starter_koin_coroutines.data.entities.summary

import com.google.gson.annotations.SerializedName

data class SummaryResponse(
    @SerializedName("Global") val globalNumbersSummary: GlobalNumbersSummary? = null,
    @SerializedName("Countries") val countriesNumbersSummary: ArrayList<CountryNumbersSummary>? = null
)

data class GlobalNumbersSummary(
    @SerializedName("NewConfirmed") val newConfirmed: Int = 0,
    @SerializedName("NewDeaths") val newDeaths: Int = 0,
    @SerializedName("NewRecovered") val newRecovered: Int = 0,
    @SerializedName("TotalConfirmed") val totalConfirmed: Int = 0,
    @SerializedName("TotalDeaths") val totalDeaths: Int = 0,
    @SerializedName("TotalRecovered") val totalRecovered: Int = 0
)

data class CountryNumbersSummary(
    @SerializedName("Country") val countryName: String? = null,
    @SerializedName("CountryCode") val countryCode: String? = null,
    @SerializedName("NewConfirmed") val newConfirmed: Int = 0,
    @SerializedName("NewDeaths") val newDeaths: Int = 0,
    @SerializedName("NewRecovered") val newRecovered: Int = 0,
    @SerializedName("TotalConfirmed") val totalConfirmed: Int = 0,
    @SerializedName("TotalDeaths") val totalDeaths: Int = 0,
    @SerializedName("TotalRecovered") val totalRecovered: Int = 0
)