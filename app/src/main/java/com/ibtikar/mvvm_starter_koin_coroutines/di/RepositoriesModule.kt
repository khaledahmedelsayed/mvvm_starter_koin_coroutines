package com.ibtikar.mvvm_starter_koin_coroutines.di

import com.ibtikar.mvvm_starter_koin_coroutines.ui.home.HomeRepository
import org.koin.dsl.module

/**
 * Created by Mohammed Hemdan on 7/26/20.
 * Email : mohammed.hemdan.faraj@gmail.com
 * Github : https://github.com/mhemdan
 */

val repositoryModule = module {
    single {
        HomeRepository(
            get(),
            get()
        )
    }

}