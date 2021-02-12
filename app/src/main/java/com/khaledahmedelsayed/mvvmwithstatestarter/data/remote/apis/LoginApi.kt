package com.khaledahmedelsayed.mvvmwithstatestarter.data.remote.apis

import com.khaledahmedelsayed.mvvmwithstatestarter.data.remote.responses.LoginResponse
import com.khaledahmedelsayed.mvvmwithstatestarter.data.remote.responses.base.ApiBaseResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginApi {

    @FormUrlEncoded
    @POST(LOGIN)
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<ApiBaseResponse<LoginResponse>>

    companion object {
        const val LOGIN = "endpoint/login"
    }
}
