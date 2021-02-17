package com.ibtikar.mvvm_starter_koin_coroutines.tests

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.google.common.truth.Truth.assertThat
import com.ibtikar.mvvm_starter_koin_coroutines.R
import com.ibtikar.mvvm_starter_koin_coroutines.Utils.MAX_TIME_OUT
import com.ibtikar.mvvm_starter_koin_coroutines.scenarios.OpenCountryDetailsScenario
import com.ibtikar.mvvm_starter_koin_coroutines.screens.HomeScreen
import com.ibtikar.mvvm_starter_koin_coroutines.ui.ContainerActivity
import com.ibtikar.mvvm_starter_koin_coroutines.ui.features.home.HomeFragment
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.koin.core.KoinComponent

class HomeUITests : TestCase(), KoinComponent {

    @get:Rule // To Annotate getter only (not property, because Junit won't recognize)
    val activityTestRule =
        ActivityScenarioRule(ContainerActivity::class.java) // Using Rule to automatically close activity after testing https://medium.com/stepstone-tech/better-tests-with-androidxs-activityscenario-in-kotlin-part-1-6a6376b713ea

    private val screen = HomeScreen()

    @Test
    fun new_And_Total_Actual_Numbers_Should_Be_Displayed() =
        run {
            screen {
                flakySafely(timeoutMs = MAX_TIME_OUT) {
                    step("Check that new and total numbers are more than zero") {
                        newConfirmed.hasNoText("0")
                        newDeaths.hasNoText("0")
                        totalConfirmed.hasNoText("0")
                        totalDeaths.hasNoText("0")
                    }
                }
            }
        }

    @Test
    fun recycler_View_Should_Be_Populated_With_Items() =
        run {
            step("Check that recycler view has items") {
                flakySafely(timeoutMs = MAX_TIME_OUT) {
                    assert(screen.countriesRecyclerView.getSize() > 0)
                }
            }
        }

    @Test
    fun recycler_View_Country_Item_Should_Be_Displayed_Properly() =
        run {
            step("Check that the first item is displayed properly") {
                screen.countriesRecyclerView {
                    firstChild<HomeScreen.CountrySummaryItem> {
                        flakySafely(timeoutMs = MAX_TIME_OUT) {
                            isDisplayed()
                            countryFlag.isDisplayed()
                            countryCases.isDisplayed()
                            countryDeaths.isDisplayed()
                        }
                    }
                }
            }
        }

    @Test
    fun on_Clicking_On_A_Country_Summary_Card_item_Should_Open_Details_Screen_NavController_way() {
        val mockNavController =
            TestNavHostController(ApplicationProvider.getApplicationContext()).apply { setGraph(R.navigation.nav_graph) }

        before {
            // Note that app will run twice, first time from the activity rule, then this one -> not ideal but just for demonstration
            launchFragmentInContainer<HomeFragment>(themeResId = R.style.AppTheme).onFragment {
                Navigation.setViewNavController(it.requireView(), mockNavController)
            }
        }.after {
            // Ignore
        }.run {
            step("Check that on clicking on an country card item, navigates to it's Details Screen") {
                scenario(OpenCountryDetailsScenario())
                assertThat(mockNavController.currentDestination?.id).isEqualTo(R.id.detailsFragment)
            }
        }
    }

    @Test
    fun on_Clicking_On_A_Country_Summary_Card_item_Should_Open_Details_Screen_Layout_Way() =
        run {
            step("Check that on clicking on an country card item, navigates to it's Details Screen") {
                scenario(OpenCountryDetailsScenario())
                screen.countryDetailsScreen.isDisplayed()
            }
        }
}
