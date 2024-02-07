package com.stopstone.newsapp.data

import com.stopstone.newsapp.data.model.Article
import com.stopstone.newsapp.data.model.BookmarkArticle
import com.stopstone.newsapp.data.model.Category
import com.stopstone.newsapp.data.source.local.AppDatabase
import com.stopstone.newsapp.data.source.local.BookmarkArticleDao
import com.stopstone.newsapp.util.DateFormatText
import javax.inject.Inject

class ArticleDetailRepository @Inject constructor(private val dao: BookmarkArticleDao) {
    suspend fun getArticle(article: Article, category: Category): BookmarkArticle? {
        return dao.getArticle(article, category)
    }

    suspend fun addBookmarkArticle(article: Article, category: Category) {
        val bookmarkArticle = BookmarkArticle(article, category, DateFormatText.getCurrentDate())
        dao.insert(bookmarkArticle)
    }

    suspend fun removeBookmarkArticle(article: Article, category: Category) {
        dao.delete(article, category)
    }
}