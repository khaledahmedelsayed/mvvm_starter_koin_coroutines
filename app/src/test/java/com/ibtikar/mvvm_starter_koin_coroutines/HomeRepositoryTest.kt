package com.ibtikar.mvvm_starter_koin_coroutines

import com.asala.loyalty.utils.InstantExecutorExtension
import com.ibtikar.mvvm_starter_koin_coroutines.common.ApplicationRunTimeException
import com.ibtikar.mvvm_starter_koin_coroutines.data.remote.apis.Covid19Api
import com.ibtikar.mvvm_starter_koin_coroutines.data.remote.responses.SummaryResponse
import com.ibtikar.mvvm_starter_koin_coroutines.data.repositories.HomeRepository
import com.ibtikar.mvvm_starter_koin_coroutines.utils.KoinTestExtension
import com.ibtikar.mvvm_starter_koin_coroutines.utils.TestContextProvider
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import retrofit2.Response
import kotlin.random.Random
import kotlin.random.nextInt


@ExtendWith(InstantExecutorExtension::class, KoinTestExtension::class)
class HomeRepositoryTest {
    private lateinit var unitUnderTest: HomeRepository

    // Mocks
    private val summaryResponse = mockk<SummaryResponse>()
    private val covid19Api = mockk<Covid19Api>()

    private val contextProvidersTest = TestContextProvider()

    @BeforeEach
    internal fun setUp() {
        unitUnderTest = HomeRepository(covid19Api, contextProvidersTest)
    }

    @Test
    fun `when invoking getCovid19Summary() with error response then ApplicationRunTimeException is thrown`() {
        runBlocking {
            //Assemble/Given
            coEvery { covid19Api.getSummary() } returns
                    Response.error(
                        Random.nextInt(300..400),
                        ResponseBody.create(MediaType.get("application/json"), "")
                    )

            //Act/Action

            //Assert/Verify
            assertThrows(ApplicationRunTimeException::class.java) {
                runBlocking { unitUnderTest.getCovid19Summary() }
            }
        }
    }

    @Test
    fun `when invoking getCovid19Summary() with success response then return SummaryResponse()`() {
        runBlocking {
            unitUnderTest = HomeRepository(covid19Api, TestContextProvider())

            //Assemble/Given
            coEvery { covid19Api.getSummary() } returns
                    Response.success(SummaryResponse())

            //Act/Action
            val response = unitUnderTest.getCovid19Summary()

            //Assert/Verify
            assertEquals(response, summaryResponse)
        }
    }
}