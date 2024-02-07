package com.stopstone.newsapp.data

import com.stopstone.newsapp.data.model.Article
import com.stopstone.newsapp.data.source.remote.NewsService
import javax.inject.Inject

class CategoryArticleRepository @Inject constructor(private val newsService: NewsService) {
    suspend fun getCategoryArticle(category: String): List<Article> {
        return newsService.getTopHeadLines(category).articles
    }
}