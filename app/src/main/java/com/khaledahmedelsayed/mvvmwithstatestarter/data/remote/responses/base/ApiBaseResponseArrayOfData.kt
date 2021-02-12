package com.khaledahmedelsayed.mvvmwithstatestarter.data.remote.responses.base

import com.google.gson.annotations.SerializedName

data class ApiBaseResponseArrayOfData<T>(
    @SerializedName("data") val data: List<T>? = null,
    @SerializedName("links") val links: Links? = null,
    @SerializedName("meta") val meta: Meta? = null

) {
    data class Meta(
        @SerializedName("current_page") val currentPage: Int? = null,
        @SerializedName("from") val from: Int? = null,
        @SerializedName("last_page") val lastPage: Int? = null,
        @SerializedName("path") val path: String? = null,
        @SerializedName("per_page") val perPage: String? = null,
        @SerializedName("to") val to: Int? = null,
        @SerializedName("total") val total: Int? = null
    )

    data class Links(
        @SerializedName("self") val self: String? = null,
        @SerializedName("first") val first: String? = null,
        @SerializedName("last") val last: String? = null,
        @SerializedName("prev") val prev: String? = null,
        @SerializedName("next") val next: String? = null
    )
}
