package com.ibtikar.mvvm_starter_koin_coroutines.data.remote.responses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.ibtikar.mvvm_starter_koin_coroutines.data.remote.entities.CountryNumbersSummary
import com.ibtikar.mvvm_starter_koin_coroutines.data.remote.entities.GlobalNumbersSummary
import kotlinx.parcelize.Parcelize

@Parcelize
data class SummaryResponse(
    @SerializedName("Global") val globalNumbersSummary: GlobalNumbersSummary? = null,
    @SerializedName("Countries") val countriesNumbersSummary: ArrayList<CountryNumbersSummary>? = null
): Parcelable