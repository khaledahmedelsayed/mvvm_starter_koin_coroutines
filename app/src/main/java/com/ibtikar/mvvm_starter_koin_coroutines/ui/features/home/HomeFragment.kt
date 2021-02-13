package com.ibtikar.mvvm_starter_koin_coroutines.ui.features.home

import android.view.View
import com.ibtikar.mvvm_starter_koin_coroutines.common.Constants.SUCCESS_TOAST_DELAY
import com.ibtikar.mvvm_starter_koin_coroutines.data.remote.entities.CountryNumbersSummary
import com.ibtikar.mvvm_starter_koin_coroutines.data.remote.entities.GlobalNumbersSummary

import com.ibtikar.mvvm_starter_koin_coroutines.databinding.FragmentHomeBinding
import com.ibtikar.mvvm_starter_koin_coroutines.ui.platform.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel, HomeViewState>() {

    override fun setupScreenDecorations() {
        super.setupScreenDecorations()
        requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

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
        binding.countriesRV.adapter = CountriesSummaryAdapter(
            onCountryCardClick = { countrySummary ->
                navigateWithData(HomeFragmentDirections.toCountryDetails(countrySummary))
            }
        )
    }

    private fun setupGlobalNumbers(globalNumbersSummary: GlobalNumbersSummary?) {
        binding.globalSummaryNumbers = globalNumbersSummary
    }

    private fun setupCountriesNumbers(list: ArrayList<CountryNumbersSummary>?) {
        list?.removeAll { it.newConfirmed == 0 && it.newDeaths == 0 } // Remove countries without reports
        (binding.countriesRV.adapter as CountriesSummaryAdapter).submitList(list)
    }
}