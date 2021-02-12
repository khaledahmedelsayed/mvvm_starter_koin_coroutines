package com.ibtikar.mvvm_starter_koin_coroutines.utils

import com.ibtikar.mvvm_starter_koin_coroutines.common.helpers.ContextProviders
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class TestContextProvider : ContextProviders() {
    override val main: CoroutineContext
        get() = Dispatchers.Unconfined
    override val io: CoroutineContext
        get() = Dispatchers.Unconfined
}
