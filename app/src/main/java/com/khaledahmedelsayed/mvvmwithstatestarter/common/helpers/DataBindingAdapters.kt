package com.khaledahmedelsayed.mvvmwithstatestarter.common.helpers

import android.view.View
import androidx.databinding.BindingAdapter
import com.khaledahmedelsayed.mvvmwithstatestarter.common.extensions.setOnSingleClickListener

object DataBindingAdapters {

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
}
