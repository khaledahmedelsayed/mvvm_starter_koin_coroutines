package com.ibtikar.mvvm_starter_koin_coroutines.utils

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import org.koin.core.KoinComponent
import org.koin.core.inject

object DataBindingAdapters : KoinComponent {

    private val context: Context by inject()

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun bindCountryFlag(view: ImageView, countryCode: String) {
        Glide
            .with(context)
            .load("https://www.countryflags.io/$countryCode/shiny/64.png")
            .into(view)
    }

}