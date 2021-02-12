package com.khaledahmedelsayed.mvvmwithstatestarter.ui.features.splash

import com.khaledahmedelsayed.mvvmwithstatestarter.databinding.FragmentSplashBinding
import com.khaledahmedelsayed.mvvmwithstatestarter.ui.platform.base.BaseFragment

class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel, SplashViewState>() {

    override fun setup() {
        viewModel.onSplashReady()
    }

    override fun render(screenViewState: SplashViewState) {
        when (screenViewState) {
            is SplashViewState.OnNewLogin -> { openFragment(ExampleBottomSheet::class.java.newInstance()) }
            is SplashViewState.OnLoggedInUser -> { /*navigateTo(R.id.navigateToHome)*/ }
        }
    }
}
