package com.ibtikar.mvvm_starter_koin_coroutines.utils

import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class KoinTestExtension : BeforeEachCallback, AfterEachCallback {

    override fun beforeEach(context: ExtensionContext?) {
        startKoin { }
    }

    override fun afterEach(context: ExtensionContext?) {
        stopKoin()
    }
}
