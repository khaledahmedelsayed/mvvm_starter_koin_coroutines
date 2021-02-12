package com.khaledahmedelsayed.mvvmwithstatestarter.di

import com.khaledahmedelsayed.mvvmwithstatestarter.ui.features.splash.SplashViewModel
import com.khaledahmedelsayed.mvvmwithstatestarter.ui.platform.base.BaseViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel { BaseViewModel(get()) }
    viewModel { SplashViewModel(get(), get()) }
}
