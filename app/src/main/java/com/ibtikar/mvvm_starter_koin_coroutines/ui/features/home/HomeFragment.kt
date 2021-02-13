package com.ibtikar.mvvm_starter_koin_coroutines.ui.features.home

import com.ibtikar.mvvm_starter_koin_coroutines.common.Constants.SUCCESS_TOAST_DELAY
import com.ibtikar.mvvm_starter_koin_coroutines.data.remote.entities.CountryNumbersSummary
import com.ibtikar.mvvm_starter_koin_coroutines.data.remote.entities.GlobalNumbersSummary

import com.ibtikar.mvvm_starter_koin_coroutines.databinding.FragmentHomeBinding
import com.ibtikar.mvvm_starter_koin_coroutines.ui.platform.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel, HomeViewState>() {

    override fun setup() {
        setupCountriesAdapter()
        viewModel.loadCovid19Summary()
    }

    override fun render(screenViewState: HomeViewState) {
        when (screenViewState) {
            is HomeViewState.OnCovidSummaryRetrieved -> {
                setupGlobalNumbers(screenViewState.summary?.globalNumbersSummary)
                setupCountriesNumbers(screenViewState.summary?.countriesNumbersSummary)
            }
        }
    }

    override fun onLoaded() {
        displaySucceedMessage("Data loaded successfully!", SUCCESS_TOAST_DELAY)
    }

    private fun setupCountriesAdapter() {
        binding.countriesRV.adapter = CountriesSummaryAdapter()
    }

    private fun setupGlobalNumbers(globalNumbersSummary: GlobalNumbersSummary?) {
        binding.globalSummaryNumbers = globalNumbersSummary
    }

    private fun setupCountriesNumbers(list: ArrayList<CountryNumbersSummary>?) {
        val covidActiveCountriesList = arrayListOf<CountryNumbersSummary>()
        list?.forEach {
            if(it.newConfirmed!=0 && it.newDeaths != 0)
                covidActiveCountriesList.add(it)
        }
        (binding.countriesRV.adapter as CountriesSummaryAdapter).submitList(covidActiveCountriesList)
    }
}