package com.stopstone.newsapp.ui.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.stopstone.newsapp.R

@BindingAdapter("iamgeUrl")
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