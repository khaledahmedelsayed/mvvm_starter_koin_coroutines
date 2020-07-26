package com.ibtikar.mvvm_starter_koin_coroutines.ui.base

import com.ibtikar.mvvm_starter_koin_coroutines.utils.coroutines.ContextProviders
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext

abstract class BaseRepository(private val contextProviders: ContextProviders) {

    suspend fun <T> launchBlock(block: suspend CoroutineScope.() -> T): T {
        return withContext(contextProviders.IO) {
            return@withContext block.invoke(this)
        }
    }
}