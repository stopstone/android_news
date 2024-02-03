package com.stopstone.newsapp.ui.extensions

import android.widget.TextView
import com.stopstone.newsapp.util.DateFormatText

fun TextView.setPublishedAt(publishedAt: String) {
    val date = DateFormatText.convertToDate(publishedAt)
    text = if (date != null) {
        DateFormatText.convertToArticleDateFormat(date)
    } else {
        ""
    }
}