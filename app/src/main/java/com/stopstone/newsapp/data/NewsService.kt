package com.stopstone.newsapp.data

import com.stopstone.newsapp.BuildConfig
import com.stopstone.newsapp.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsService {
    // @Header("X-Api-Key: ${Constants.KEY_API}")
    @Headers("X-Api-Key: ${BuildConfig.API_KEY}")
    @GET("v2/top-headlines")
    suspend fun getTopHeadLines(
        @Query("category") category: String? = null,
        @Query("country") country: String = "kr",
    ): TopHeadlinesResponse

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
