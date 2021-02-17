package com.ibtikar.mvvm_starter_koin_coroutines.scenarios

import com.ibtikar.mvvm_starter_koin_coroutines.screens.DetailsScreen
import com.kaspersky.kaspresso.testcases.api.scenario.Scenario
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext

class OpenBottomSheetScenario() : Scenario() {
    private val detailsScreen = DetailsScreen()
    override val steps: TestContext<Unit>.() -> Unit = {
            scenario(OpenCountryDetailsScenario())
            step("Click on send button in bottom sheet") {
                detailsScreen.sendButton.click()
            }
    }
}
