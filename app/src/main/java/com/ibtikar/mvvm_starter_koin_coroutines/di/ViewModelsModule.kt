package com.ibtikar.mvvm_starter_koin_coroutines.di

import com.ibtikar.mvvm_starter_koin_coroutines.ui.features.details.DetailsViewModel
import com.ibtikar.mvvm_starter_koin_coroutines.ui.features.home.HomeViewModel
import com.ibtikar.mvvm_starter_koin_coroutines.ui.platform.base.BaseViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel { BaseViewModel(get()) }
    viewModel { HomeViewModel(get(), get()) }
    viewModel { DetailsViewModel(get()) }
}
