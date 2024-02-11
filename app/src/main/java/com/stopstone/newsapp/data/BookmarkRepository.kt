package com.stopstone.newsapp.data

import com.stopstone.newsapp.data.model.BookmarkArticle
import com.stopstone.newsapp.data.source.local.BookmarkArticleDao
import javax.inject.Inject

class BookmarkRepository @Inject constructor(private val dao: BookmarkArticleDao) {
    suspend fun getAllArticles(): List<BookmarkArticle> {
        return dao.getAllArticles()
    }
}