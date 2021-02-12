package com.khaledahmedelsayed.mvvmwithstatestarter.utils

import com.khaledahmedelsayed.mvvmwithstatestarter.common.helpers.ContextProviders
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class TestContextProvider : ContextProviders() {
    override val main: CoroutineContext
        get() = Dispatchers.Unconfined
    override val io: CoroutineContext
        get() = Dispatchers.Unconfined
}
