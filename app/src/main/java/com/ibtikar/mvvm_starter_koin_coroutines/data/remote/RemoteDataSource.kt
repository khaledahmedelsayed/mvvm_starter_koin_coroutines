package com.ibtikar.mvvm_starter_koin_coroutines.data.remote

import com.ibtikar.mvvm_starter_koin_coroutines.data.remote.apis.Covid19Api

interface RemoteDataSource {

}

interface ExampleRemoteDataSource : RemoteDataSource {
    val covid19Api: Covid19Api
}