package com.khaledahmedelsayed.mvvmwithstatestarter.ui.base

import androidx.lifecycle.Observer
import com.asala.loyalty.utils.InstantExecutorExtension
import com.khaledahmedelsayed.mvvmwithstatestarter.common.ApplicationRunTimeException
import com.khaledahmedelsayed.mvvmwithstatestarter.common.types.ErrorType
import com.khaledahmedelsayed.mvvmwithstatestarter.ui.platform.base.BaseViewModel
import com.khaledahmedelsayed.mvvmwithstatestarter.ui.platform.base.BaseViewState
import com.khaledahmedelsayed.mvvmwithstatestarter.utils.KoinTestExtension
import com.khaledahmedelsayed.mvvmwithstatestarter.utils.TestContextProvider
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyOrder
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@ExtendWith(InstantExecutorExtension::class, KoinTestExtension::class)
internal class BaseViewModelTest {
    private var _unitUnderTest: BaseViewModel? = null
    private val unitUnderTest get() = _unitUnderTest!!

    // Mocks
    private val stateObserver = mockk<Observer<BaseViewState>>(relaxed = true)

    @BeforeEach
    internal fun setUp() {
        _unitUnderTest = BaseViewModel(TestContextProvider())
        unitUnderTest.internalState.observeForever(stateObserver)
    }

    @ParameterizedTest
    @ValueSource(booleans = [true, false])
    fun `when invoking launchSuspendingBlock() with a displayLoaderInput then Loading(displayLoaderInput) is posted`(
        displayLoaderInput: Boolean,
    ) {
        // Act/Action
        unitUnderTest.launchSuspendingBlock(displayLoader = displayLoaderInput) { }

        // Assert/Verify
        verify { stateObserver.onChanged(BaseViewState.Loading(displayLoaderInput)) }
    }

    @Test
    fun `when invoking launchSuspendingBlock() with an exception thrown in block then Error() is posted`() {
        // Act/Action
        val dummyException =
            ApplicationRunTimeException(errorType = ErrorType.UnexpectedErrorType)

        unitUnderTest.launchSuspendingBlock { throw dummyException }

        // Assert/Verify
        verifyOrder {
            stateObserver.onChanged(BaseViewState.Loading())
            stateObserver.onChanged(BaseViewState.Error(dummyException))
        }
    }
}
