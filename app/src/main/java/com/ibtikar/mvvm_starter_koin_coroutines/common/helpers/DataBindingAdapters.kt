package com.ibtikar.mvvm_starter_koin_coroutines.common.helpers

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation
import com.ibtikar.mvvm_starter_koin_coroutines.common.extensions.setOnSingleClickListener
import org.koin.core.KoinComponent
import org.koin.core.inject

object DataBindingAdapters : KoinComponent {
    private val context: Context by inject()

    @JvmStatic
    @BindingAdapter("onSingleClick")
    fun setOnSingleClick(view: View, lambda: OnSingleClick) {
        view.setOnSingleClickListener {
            lambda.invoke()
        }
    }

    interface OnSingleClick {
        fun invoke()
    }

    @JvmStatic
    @BindingAdapter("countryCodeFlag")
    fun bindCountryFlagSmall(imageView: ImageView, countryCode: String) {
        imageView.load("https://www.countryflags.io/$countryCode/shiny/64.png") {
            crossfade(true)
            transformations(CircleCropTransformation())
        }
    }
}
