package com.khaledahmedelsayed.mvvmwithstatestarter.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.khaledahmedelsayed.mvvmwithstatestarter.BuildConfig
import com.khaledahmedelsayed.mvvmwithstatestarter.common.helpers.ApiPrettyLogger
import com.khaledahmedelsayed.mvvmwithstatestarter.common.types.SharedPrefsKeys
import com.khaledahmedelsayed.mvvmwithstatestarter.data.local.SharedPreferencesUtils
import com.khaledahmedelsayed.mvvmwithstatestarter.data.local.get
import com.khaledahmedelsayed.mvvmwithstatestarter.data.remote.apis.LoginApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val NetworkModule = module {

    fun provideLoggerInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor(ApiPrettyLogger())
        interceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return interceptor
    }

    fun provideAuthenticationInterceptor(sharedPrefUtils: SharedPreferencesUtils) =
        Interceptor { chain ->
            var request = chain.request()
            request = request.newBuilder()
                .addHeader("Authorization", "Bearer " + sharedPrefUtils.get(SharedPrefsKeys.ACCESS_TOKEN.name))
                .build()
            chain.proceed(request)
        }

    fun provideOkHttpClient(
        provideAuthenticationInterceptor: Interceptor,
        loggerInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(TIMEOUT_DELAY, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_DELAY, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_DELAY, TimeUnit.SECONDS)
            .addInterceptor(provideAuthenticationInterceptor)
            .addInterceptor(loggerInterceptor)
            .build()
    }

    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(factory))
            .client(client)
            .build()
    }

    fun provideLoginApi(retrofit: Retrofit) = retrofit.create(LoginApi::class.java)

    // Retrofit
    single { provideAuthenticationInterceptor(get()) }
    single { provideOkHttpClient(get(), provideLoggerInterceptor()) }
    single { provideGson() }
    single { provideRetrofit(get(), get()) }

    // APIs
    single { provideLoginApi(get()) }
}

const val TIMEOUT_DELAY = 10L
