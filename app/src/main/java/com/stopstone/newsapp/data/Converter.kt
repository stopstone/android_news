package com.stopstone.newsapp.data

import androidx.room.TypeConverter
import com.google.gson.Gson

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