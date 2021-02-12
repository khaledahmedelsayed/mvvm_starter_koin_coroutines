package com.khaledahmedelsayed.mvvmwithstatestarter.data.remote.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Customer(

    @SerializedName("id") val id: Int? = null,
    @SerializedName("full_name") val fullName: String? = null,
    @SerializedName("email") val email: String? = null,
    @SerializedName("mobile_number") val mobileNumber: String? = "Not available",

) : Parcelable
