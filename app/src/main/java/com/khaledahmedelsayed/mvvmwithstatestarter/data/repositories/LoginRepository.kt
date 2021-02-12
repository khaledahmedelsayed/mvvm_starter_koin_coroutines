package com.khaledahmedelsayed.mvvmwithstatestarter.data.repositories

import com.khaledahmedelsayed.mvvmwithstatestarter.common.helpers.ContextProviders
import com.khaledahmedelsayed.mvvmwithstatestarter.common.types.SharedPrefsKeys
import com.khaledahmedelsayed.mvvmwithstatestarter.data.local.SharedPreferencesUtils
import com.khaledahmedelsayed.mvvmwithstatestarter.data.local.get
import com.khaledahmedelsayed.mvvmwithstatestarter.data.local.set
import com.khaledahmedelsayed.mvvmwithstatestarter.data.remote.apis.LoginApi

class LoginRepository(
    private val api: LoginApi,
    private val sharedPrefs: SharedPreferencesUtils,
    contextProviders: ContextProviders
) :
    BaseRepository(contextProviders) {
    suspend fun requestLogin(email: String, password: String) =
        executeNetworkCall {
            val loginResponse = api.login(email, password)
            saveAccessToken(loginResponse.body()?.data?.accessToken)

            loginResponse
        }

    private fun saveAccessToken(accessToken: String?) {
        sharedPrefs.set(SharedPrefsKeys.ACCESS_TOKEN.name, accessToken)
    }

    fun isLoggedIn() = sharedPrefs.get<String>(SharedPrefsKeys.ACCESS_TOKEN.name) != null
}
