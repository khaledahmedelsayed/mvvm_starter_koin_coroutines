package com.ibtikar.mvvm_starter_koin_coroutines.ui.home

import com.ibtikar.mvvm_starter_koin_coroutines.R
import com.ibtikar.mvvm_starter_koin_coroutines.data.entities.summary.CountryNumbersSummary
import com.ibtikar.mvvm_starter_koin_coroutines.data.entities.summary.GlobalNumbersSummary
import com.ibtikar.mvvm_starter_koin_coroutines.databinding.FragmentHomeBinding
import com.ibtikar.mvvm_starter_koin_coroutines.ui.base.BaseFragment
import com.ibtikar.mvvm_starter_koin_coroutines.ui.base.ViewState
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val viewModel: HomeViewModel by viewModel()

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun setup() {
        setupCountriesAdapter()
        viewModel.loadCovid19Summary()
    }

    override fun render(currentState: ViewState) {
        when (currentState) {
            is HomeViewState.OnCovidSummaryRetrieved -> {
                setupGlobalNumbers(currentState.loadedData?.globalNumbersSummary)
                setupCountriesNumbers(currentState.loadedData?.countriesNumbersSummary)
            }
        }
    }

    private fun setupCountriesAdapter() {
        countriesRV.adapter = CountriesSummaryAdapter()
    }

    private fun setupGlobalNumbers(globalNumbersSummary: GlobalNumbersSummary?) {
        binding.globalSummaryNumbers = globalNumbersSummary
    }

    private fun setupCountriesNumbers(list: ArrayList<CountryNumbersSummary>?) {
        (countriesRV.adapter as CountriesSummaryAdapter).submitList(list)
    }
}