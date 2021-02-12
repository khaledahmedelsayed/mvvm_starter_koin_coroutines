package com.ibtikar.mvvm_starter_koin_coroutines

import com.ibtikar.mvvm_starter_koin_coroutines.common.ApplicationRunTimeException
import com.ibtikar.mvvm_starter_koin_coroutines.common.Utils
import com.ibtikar.mvvm_starter_koin_coroutines.data.remote.apis.Covid19Api
import com.ibtikar.mvvm_starter_koin_coroutines.data.remote.responses.SummaryResponse
import com.ibtikar.mvvm_starter_koin_coroutines.data.remote.responses.base.ApiBaseResponse
import com.ibtikar.mvvm_starter_koin_coroutines.data.repositories.HomeRepository
import com.ibtikar.mvvm_starter_koin_coroutines.utils.TestContextProvider
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import kotlin.random.Random
import kotlin.random.nextInt

@RunWith(MockitoJUnitRunner::class)
class HomeRepositoryTest {

    @MockK
    lateinit var covid19Api: Covid19Api

    @MockK
    lateinit var summaryResponse: SummaryResponse

    lateinit var homeRepository: HomeRepository

    private val contextProvidersTest = TestContextProvider()

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        homeRepository = HomeRepository(covid19Api, contextProvidersTest)
        coEvery { Utils.isConnectedToNetwork() } returns true
    }

    @Test(expected = ApplicationRunTimeException::class)  //Assert/Verify
    fun `when invoking getCovid19Summary() with error response then ApplicationRunTimeException is thrown`() {
        runBlocking {
            //Assemble/Given
            coEvery { covid19Api.getSummary() } returns
                    Response.error(
                        Random.nextInt(300..400),
                        ResponseBody.create(MediaType.get("application/json"), "")
                    )

            //Act/Action
            homeRepository.getCovid19Summary()
        }
    }

    @Test
    fun `when invoking getCovid19Summary() with success response then return SummaryResponse()`() {
        runBlocking {
            //Assemble/Given
            coEvery { covid19Api.getSummary() } returns
                    Response.success(ApiBaseResponse(data = SummaryResponse()))

            //Act/Action
            val response = homeRepository.getCovid19Summary()

            //Assert/Verify
            assertEquals(response, summaryResponse)
        }
    }
}