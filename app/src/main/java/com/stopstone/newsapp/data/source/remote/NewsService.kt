package com.stopstone.newsapp.data.source.remote

import com.stopstone.newsapp.BuildConfig
import com.stopstone.newsapp.data.model.TopHeadlinesResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
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
}
