package com.ibtikar.mvvm_starter_koin_coroutines.scenarios

import com.ibtikar.mvvm_starter_koin_coroutines.Utils.MAX_TIME_OUT
import com.ibtikar.mvvm_starter_koin_coroutines.screens.HomeScreen
import com.kaspersky.kaspresso.testcases.api.scenario.Scenario
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext

class OpenCountryDetailsScenario() : Scenario() {
    private val homeScreen = HomeScreen()

    override val steps: TestContext<Unit>.() -> Unit = {
        flakySafely(MAX_TIME_OUT) {
            step("Click on the first country card") {
                homeScreen.countriesRecyclerView.firstChild<HomeScreen.CountrySummaryItem> { click() }
            }
        }
    }
}
