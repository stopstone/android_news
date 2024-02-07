package com.stopstone.newsapp.data.source.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.stopstone.newsapp.data.model.Article

class Converter {

    @TypeConverter
    fun articleToJson(article: Article): String {
        val gson = Gson()
        return gson.toJson(article)
    }

    @TypeConverter
    fun articleFromJson(json: String): Article {
        val gson = Gson()
        return gson.fromJson(json, Article::class.java)
    }
}