package com.stopstone.newsapp.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object DateFormatText {
    private const val DATE_YEAR_MONTH_DAY_PATTERN = "yyyy-MM-dd'T'hh:mm:ss'Z'"
    private const val DATE_ARTICLE_PUBLISHED_AT_PATTERN = "yy/MM/dd"

    fun convertToDate(publishedAt: String): Date? {
        val simpleDateFormat = SimpleDateFormat(DATE_YEAR_MONTH_DAY_PATTERN, Locale.KOREA)
        return simpleDateFormat.parse(publishedAt)
    }

    fun convertToArticleDateFormat(date: Date): String {
        val newFormat = SimpleDateFormat(DATE_ARTICLE_PUBLISHED_AT_PATTERN, Locale.KOREA)
        return newFormat.format(date)
    }

    fun getCurrentDate(): String {
        val simpleDateFormat = SimpleDateFormat(DATE_YEAR_MONTH_DAY_PATTERN, Locale.KOREA)
        val currentDate = Calendar.getInstance(TimeZone.getTimeZone("UTC")).time
        return simpleDateFormat.format(currentDate)

    }
}