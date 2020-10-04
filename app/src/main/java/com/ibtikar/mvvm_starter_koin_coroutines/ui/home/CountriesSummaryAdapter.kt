package com.ibtikar.mvvm_starter_koin_coroutines.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ibtikar.mvvm_starter_koin_coroutines.data.entities.summary.CountryNumbersSummary
import com.ibtikar.mvvm_starter_koin_coroutines.databinding.ItemCountrySummaryBinding


class CountriesSummaryAdapter :
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
        holder.bindViews(getItem(position))
    }

    inner class SummaryItemHolder(private val binder: ItemCountrySummaryBinding) :
        RecyclerView.ViewHolder(binder.root) {
        fun bindViews(item: CountryNumbersSummary) {
            binder.countrySummaryNumbers = item
            binder.executePendingBindings()
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