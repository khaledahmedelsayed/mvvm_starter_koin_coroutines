package com.ibtikar.mvvm_starter_koin_coroutines.ui.features.details

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.ibtikar.mvvm_starter_koin_coroutines.databinding.FragmentDetailsBinding
import com.ibtikar.mvvm_starter_koin_coroutines.ui.platform.base.BaseFragment

class DetailsFragment : BaseFragment<FragmentDetailsBinding, DetailsViewModel, DetailsViewState>() {

    private val args: DetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(args) {
            binding.countrySummaryNumbers = countrySummary

        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun render(screenViewState: DetailsViewState) { }

    fun displayBottomSheet() {
        openFragment(ExampleBottomSheet())
    }
}