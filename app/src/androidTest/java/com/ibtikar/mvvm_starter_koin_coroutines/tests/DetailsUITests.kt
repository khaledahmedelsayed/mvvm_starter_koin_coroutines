package com.ibtikar.mvvm_starter_koin_coroutines.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.ibtikar.mvvm_starter_koin_coroutines.Utils.MAX_TIME_OUT
import com.ibtikar.mvvm_starter_koin_coroutines.scenarios.OpenBottomSheetScenario
import com.ibtikar.mvvm_starter_koin_coroutines.scenarios.OpenCountryDetailsScenario
import com.ibtikar.mvvm_starter_koin_coroutines.screens.DetailsScreen
import com.ibtikar.mvvm_starter_koin_coroutines.ui.ContainerActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.koin.core.KoinComponent

class DetailsUITests : TestCase(), KoinComponent {

    @get:Rule
    val activityTestRule = ActivityScenarioRule(ContainerActivity::class.java)

    private val screen = DetailsScreen()

    @Test
    fun details_Screen_Should_Contain_Country_Flag_And_Name() =
        run {
            scenario(OpenCountryDetailsScenario())
            screen {
                step("Check that country details are displayed") {
                    flakySafely(timeoutMs = MAX_TIME_OUT) {
                        countryFlag.isDisplayed()
                        countryName.isDisplayed()
                    }
                }
            }
        }

    @Test
    fun on_Clicking_Send_Button_Should_Open_Bottom_Sheet() =
        run {
            scenario(OpenBottomSheetScenario())
            step("Check that bottom sheet is displayed") {
                screen.bottomSheet.isDisplayed()
            }
        }

    @Test
    fun on_Clicking_Send_Report_Button_In_Bottom_Sheet_Should_Open_Dialog() =
        run {
            scenario(OpenBottomSheetScenario())
            step("Click on send report button in bottom sheet") {
                screen.sendReportButton.click()
            }
            step("Check that dialog is displayed") {
                screen.dialog.isDisplayed()
            }
        }
}
