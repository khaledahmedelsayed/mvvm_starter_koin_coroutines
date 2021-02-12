package com.ibtikar.mvvm_starter_koin_coroutines.common.extensions

import android.view.View
import android.widget.ImageView
import coil.load
import coil.transform.CircleCropTransformation

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.setOnSingleClickListener(
    throttleDelay: Long = 1000L,
    onClick: (View) -> Unit
) {
    setOnClickListener {
        onClick(this)
        isClickable = false
        postDelayed({ isClickable = true }, throttleDelay)
    }
}

fun ImageView.loadImage(imageUrl: String?) {
    this.load(imageUrl) {
        crossfade(true)
//        placeholder(R.drawable.placeholder)
        transformations(CircleCropTransformation())
    }
}
