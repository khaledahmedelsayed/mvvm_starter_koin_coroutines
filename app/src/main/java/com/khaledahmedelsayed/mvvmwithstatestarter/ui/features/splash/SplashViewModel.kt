package com.khaledahmedelsayed.mvvmwithstatestarter.ui.features.splash

import com.khaledahmedelsayed.mvvmwithstatestarter.common.Constants.SPLASH_DELAY
import com.khaledahmedelsayed.mvvmwithstatestarter.common.helpers.ContextProviders
import com.khaledahmedelsayed.mvvmwithstatestarter.data.repositories.LoginRepository
import com.khaledahmedelsayed.mvvmwithstatestarter.ui.platform.base.BaseViewModel
import kotlinx.coroutines.delay

class SplashViewModel(
    private val loginRepository: LoginRepository,
    contextProviders: ContextProviders,
) : BaseViewModel(contextProviders) {

    fun onSplashReady() {
        launchSuspendingBlock(displayLoader = true) {
            delay(SPLASH_DELAY)
            when {
                loginRepository.isLoggedIn() ->
                    internalState.value =
                        SplashViewState.OnLoggedInUser
                else -> internalState.value = SplashViewState.OnNewLogin
            }
        }
    }
}
