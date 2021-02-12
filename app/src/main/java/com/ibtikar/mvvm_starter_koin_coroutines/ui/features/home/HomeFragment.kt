package com.ibtikar.mvvm_starter_koin_coroutines.ui.features.home

import com.ibtikar.mvvm_starter_koin_coroutines.data.remote.entities.CountryNumbersSummary
import com.ibtikar.mvvm_starter_koin_coroutines.data.remote.entities.GlobalNumbersSummary

import com.ibtikar.mvvm_starter_koin_coroutines.databinding.FragmentHomeBinding
import com.ibtikar.mvvm_starter_koin_coroutines.ui.home.HomeViewState
import com.ibtikar.mvvm_starter_koin_coroutines.ui.platform.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel,HomeViewState>() {

    override fun setup() {
        setupCountriesAdapter()
        viewModel.loadCovid19Summary()
    }

    override fun render(screenViewState: HomeViewState) {
        when (screenViewState) {
            is HomeViewState.OnCovidSummaryRetrieved -> {
                setupGlobalNumbers(screenViewState.loadedData?.globalNumbersSummary)
                setupCountriesNumbers(screenViewState.loadedData?.countriesNumbersSummary)
            }
        }
    }

    private fun setupCountriesAdapter() {
        binding.countriesRV.adapter = CountriesSummaryAdapter()
    }

    private fun setupGlobalNumbers(globalNumbersSummary: GlobalNumbersSummary?) {
        binding.globalSummaryNumbers = globalNumbersSummary
    }

    private fun setupCountriesNumbers(list: ArrayList<CountryNumbersSummary>?) {
        (binding.countriesRV.adapter as CountriesSummaryAdapter).submitList(list)
    }
}