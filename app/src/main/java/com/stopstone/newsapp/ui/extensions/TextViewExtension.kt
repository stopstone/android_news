package com.stopstone.newsapp.ui.extensions

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.stopstone.newsapp.util.DateFormatText

@BindingAdapter("dateFormat")

fun TextView.setPublishedAt(publishedAt: String) {
    val date = DateFormatText.convertToDate(publishedAt)
    text = if (date != null) {
        DateFormatText.convertToArticleDateFormat(date)
    } else {
        ""
    }
}