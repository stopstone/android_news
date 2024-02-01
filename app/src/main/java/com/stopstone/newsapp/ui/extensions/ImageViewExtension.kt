package com.stopstone.newsapp.ui.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.stopstone.newsapp.R

fun ImageView.load(url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide
            .with(this)
            .load(url)
            .error(R.color.gray)
            .placeholder(R.color.gray)
            .into(this)
    } else {
        setImageResource(R.color.gray)
    }
}