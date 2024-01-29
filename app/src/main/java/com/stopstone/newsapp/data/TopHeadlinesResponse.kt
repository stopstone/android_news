package com.stopstone.newsapp.data

data class TopHeadlinesResponse(
    val status: String,
    val totalResults: String,
    val articles: List<Article>,
)
