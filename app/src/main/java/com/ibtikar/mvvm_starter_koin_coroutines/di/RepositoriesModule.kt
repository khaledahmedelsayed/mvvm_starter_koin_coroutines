package com.ibtikar.mvvm_starter_koin_coroutines.di

import com.ibtikar.mvvm_starter_koin_coroutines.data.repositories.BaseRepository
import com.ibtikar.mvvm_starter_koin_coroutines.data.repositories.HomeRepository
import org.koin.dsl.module

val RepositoryModule = module {
    single { BaseRepository(get(), get()) }
    single { HomeRepository(get(), get()) }
}
