package com.stopstone.newsapp.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("v2/top-headlines")
    fun getTopHeadLines(
        @Query("category") category: String,
        @Query("apiKey") apiKey: String
    ): Call<TopHeadlinesResponse>

    companion object {
        fun create(): NewsService {
            return Retrofit.Builder()
                .baseUrl("https://newsapi.org/")
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsService::class.java)
        }

        private fun getClient(): OkHttpClient {
            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            return OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
        }
    }
}
