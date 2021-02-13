package com.ibtikar.mvvm_starter_koin_coroutines

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ibtikar.mvvm_starter_koin_coroutines.data.remote.entities.GlobalNumbersSummary
import com.ibtikar.mvvm_starter_koin_coroutines.data.remote.responses.SummaryResponse
import com.ibtikar.mvvm_starter_koin_coroutines.data.remote.responses.base.ApiBaseResponse
import com.ibtikar.mvvm_starter_koin_coroutines.data.repositories.HomeRepository
import com.ibtikar.mvvm_starter_koin_coroutines.ui.features.home.HomeViewModel
import com.ibtikar.mvvm_starter_koin_coroutines.ui.features.home.HomeViewState
import com.ibtikar.mvvm_starter_koin_coroutines.ui.platform.base.BaseViewState
import com.ibtikar.mvvm_starter_koin_coroutines.utils.TestContextProvider
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerifyOrder
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    //  @get:Rule
    @Rule
    @JvmField
    val liveDataTestRule = InstantTaskExecutorRule()

    @MockK
    lateinit var stateObserver: Observer<BaseViewState>

    @MockK
    lateinit var homeRepository: HomeRepository

    lateinit var homeViewModel: HomeViewModel

    private val contextProvidersTest = TestContextProvider()

    private val dummyNumbersSummary = GlobalNumbersSummary()

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        homeViewModel = HomeViewModel(homeRepository, contextProvidersTest)
        homeViewModel.internalState.observeForever(stateObserver)
    }

    @Test
    fun `when invoking loadCovid19Summary() with data being loaded successfully then change view state to LOADED`() {
        //Assemble/Given
        coEvery { homeRepository.getCovid19Summary() } returns ApiBaseResponse(data = SummaryResponse(dummyNumbersSummary))

        //Act/Action
        homeViewModel.loadCovid19Summary()

        //Assert/Verify
        coVerifyOrder {
            stateObserver.onChanged(BaseViewState.Loading())
            stateObserver.onChanged(
                HomeViewState.OnCovidSummaryRetrieved(
                    SummaryResponse(
                        dummyNumbersSummary
                    )
                )
            )
        }
    }

}