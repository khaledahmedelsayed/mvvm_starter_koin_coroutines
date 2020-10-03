package com.ibtikar.mvvm_starter_koin_coroutines.data.remote

import com.ibtikar.mvvm_starter_koin_coroutines.data.remote.apis.Covid19Api
import retrofit2.Retrofit

class RemoteDataSourceImpl(retrofit: Retrofit) :
    RemoteDataSource, ExampleRemoteDataSource {
    override val covid19Api: Covid19Api = retrofit.create(Covid19Api::class.java)

}
