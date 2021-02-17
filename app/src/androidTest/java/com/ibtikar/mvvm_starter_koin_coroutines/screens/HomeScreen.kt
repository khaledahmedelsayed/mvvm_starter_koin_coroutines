package com.ibtikar.mvvm_starter_koin_coroutines.screens

import android.view.View
import com.agoda.kakao.common.views.KView
import com.agoda.kakao.image.KImageView
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import com.ibtikar.mvvm_starter_koin_coroutines.R
import com.ibtikar.mvvm_starter_koin_coroutines.data.remote.responses.SummaryResponse
import org.hamcrest.Matcher

class HomeScreen : Screen<HomeScreen>() {
    val newConfirmed = KTextView { withId(R.id.confirmedNewValueTv) }
    val newDeaths = KTextView { withId(R.id.deathsNewValueTv) }
    val totalConfirmed = KTextView { withId(R.id.confirmedTotalValueTv) }
    val totalDeaths = KTextView { withId(R.id.deathsTotalValueTv) }

    val countriesRecyclerView = KRecyclerView(
        builder = { withId(R.id.countriesRV) },
        itemTypeBuilder = { itemType(::CountrySummaryItem) }
    )

    class CountrySummaryItem(parent: Matcher<View>) : KRecyclerItem<SummaryResponse>(parent) {
        val countryFlag = KImageView(parent) { withId(R.id.countryImageView) }
        val countryCases = KTextView(parent) { withId(R.id.countryCasesTV) }
        val countryDeaths = KTextView(parent) { withId(R.id.countryDeathsTV) }
    }

    val countryDetailsScreen = KView { withId(R.id.detailsLayout) }
}
