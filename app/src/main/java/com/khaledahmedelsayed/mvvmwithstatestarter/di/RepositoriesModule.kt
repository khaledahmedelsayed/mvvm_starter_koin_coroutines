package com.khaledahmedelsayed.mvvmwithstatestarter.di

import com.khaledahmedelsayed.mvvmwithstatestarter.data.repositories.BaseRepository
import com.khaledahmedelsayed.mvvmwithstatestarter.data.repositories.LoginRepository
import org.koin.dsl.module

val RepositoryModule = module {
    single { BaseRepository(get(), get()) }
    single { LoginRepository(get(), get(), get()) }
}
