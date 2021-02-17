package com.ibtikar.mvvm_starter_koin_coroutines

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.asala.loyalty.utils.InstantExecutorExtension
import com.ibtikar.mvvm_starter_koin_coroutines.data.remote.entities.GlobalNumbersSummary
import com.ibtikar.mvvm_starter_koin_coroutines.data.remote.responses.SummaryResponse
import com.ibtikar.mvvm_starter_koin_coroutines.data.remote.responses.base.ApiBaseResponse
import com.ibtikar.mvvm_starter_koin_coroutines.data.repositories.HomeRepository
import com.ibtikar.mvvm_starter_koin_coroutines.ui.features.home.HomeViewModel
import com.ibtikar.mvvm_starter_koin_coroutines.ui.features.home.HomeViewState
import com.ibtikar.mvvm_starter_koin_coroutines.ui.platform.base.BaseViewState
import com.ibtikar.mvvm_starter_koin_coroutines.utils.KoinTestExtension
import com.ibtikar.mvvm_starter_koin_coroutines.utils.TestContextProvider
import io.mockk.coEvery
import io.mockk.coVerifyOrder
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class, KoinTestExtension::class)
class HomeViewModelTest {
    private lateinit var unitUnderTest: HomeViewModel

    // Mocks
    private val homeRepository = mockk<HomeRepository>()
    private val stateObserver = mockk<Observer<BaseViewState>>(relaxed = true)

    private val contextProvidersTest = TestContextProvider()
    private val dummyNumbersSummary = GlobalNumbersSummary()

    @BeforeEach
    internal fun setUp() {
        unitUnderTest = HomeViewModel(homeRepository, contextProvidersTest)
        unitUnderTest.baseViewState.observeForever(stateObserver)
    }

    @Test
    fun `when invoking loadCovid19Summary() with data being loaded successfully then change view state to LOADED`() {
        //Assemble/Given
        coEvery { homeRepository.getCovid19Summary() } returns ApiBaseResponse(data = SummaryResponse(dummyNumbersSummary))

        //Act/Action
        unitUnderTest.loadCovid19Summary()

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