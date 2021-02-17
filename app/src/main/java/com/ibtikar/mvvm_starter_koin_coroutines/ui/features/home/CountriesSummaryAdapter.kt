package com.ibtikar.mvvm_starter_koin_coroutines.ui.features.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ibtikar.mvvm_starter_koin_coroutines.data.remote.entities.CountryNumbersSummary
import com.ibtikar.mvvm_starter_koin_coroutines.databinding.ItemCountrySummaryBinding

class CountriesSummaryAdapter(val onCountryCardClick: (countrySummary: CountryNumbersSummary) -> Unit) :
    ListAdapter<CountryNumbersSummary, CountriesSummaryAdapter.SummaryItemHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SummaryItemHolder {
        return SummaryItemHolder(
            ItemCountrySummaryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SummaryItemHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    inner class SummaryItemHolder(private val binder: ItemCountrySummaryBinding) :
        RecyclerView.ViewHolder(binder.root) {
        fun bindView(item: CountryNumbersSummary) {
            binder.countrySummaryNumbers = item
            binder.executePendingBindings()

            binder.root.setOnClickListener { onCountryCardClick(item) }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<CountryNumbersSummary>() {
        override fun areItemsTheSame(
            oldItem: CountryNumbersSummary,
            newItem: CountryNumbersSummary
        ): Boolean {
            return oldItem.countryCode == newItem.countryCode
        }

        override fun areContentsTheSame(
            oldItem: CountryNumbersSummary,
            newItem: CountryNumbersSummary
        ): Boolean {
            return oldItem == newItem
        }
    }
}
