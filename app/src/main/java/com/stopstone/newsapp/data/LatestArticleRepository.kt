package com.stopstone.newsapp.data

import com.stopstone.newsapp.data.model.Article
import com.stopstone.newsapp.data.source.remote.NewsService
import javax.inject.Inject

class LatestArticleRepository @Inject constructor(private val newsService: NewsService) {
    suspend fun getLatestArticle(): List<Article> {
        return newsService.getTopHeadLines().articles
    }
}