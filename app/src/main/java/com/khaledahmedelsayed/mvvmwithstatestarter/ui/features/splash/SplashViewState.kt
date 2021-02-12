package com.khaledahmedelsayed.mvvmwithstatestarter.ui.features.splash

import com.khaledahmedelsayed.mvvmwithstatestarter.ui.platform.base.BaseViewState

sealed class SplashViewState : BaseViewState() {
    object OnNewLogin : SplashViewState()
    object OnLoggedInUser : SplashViewState()
}
