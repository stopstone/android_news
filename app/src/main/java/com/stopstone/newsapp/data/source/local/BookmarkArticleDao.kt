package com.stopstone.newsapp.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.stopstone.newsapp.data.model.Article
import com.stopstone.newsapp.data.model.BookmarkArticle
import com.stopstone.newsapp.data.model.Category

@Dao
interface BookmarkArticleDao {

    @Insert
    suspend fun insert(article: BookmarkArticle)

    @Query("DELETE FROM bookmark_articles WHERE article=:article AND category=:category")
    suspend fun delete(article: Article, category: Category)

    @Query("SELECT * FROM bookmark_articles WHERE article =:article AND category =:category")
    suspend fun getArticle(article: Article, category: Category) : BookmarkArticle?

    @Query("SELECT * FROM bookmark_articles ORDER BY category ASC, added_date DESC")
    suspend fun getAllArticles() : List<BookmarkArticle>
}