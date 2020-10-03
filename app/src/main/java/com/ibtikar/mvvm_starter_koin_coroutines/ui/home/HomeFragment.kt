package com.ibtikar.mvvm_starter_koin_coroutines.ui.home

import com.ibtikar.mvvm_starter_koin_coroutines.R
import com.ibtikar.mvvm_starter_koin_coroutines.databinding.FragmentHomeBinding
import com.ibtikar.mvvm_starter_koin_coroutines.ui.base.BaseFragment
import com.ibtikar.mvvm_starter_koin_coroutines.ui.base.ViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val viewModel: HomeViewModel by viewModel()

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun setup() {
        viewModel.loadCovid19Summary()
    }

    override fun render(state: ViewState) {
        when (state) {
            is HomeViewState.OnCovidSummaryRetrieved -> binding.globalSummaryNumbers =
                state.data?.globalNumbersSummary
        }
    }
}