package com.stopstone.newsapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookmarkArticleDao {

    @Insert
    suspend fun insert(article: BookmarkArticle)

    @Query("DELETE FROM bookmark_articles WHERE article=:article AND category=:category")
    suspend fun delete(article: Article, category: Category)

    @Query("SELECT * FROM bookmark_articles WHERE article =:article AND category =:category")
    suspend fun getArticle(article: Article, category: Category) : BookmarkArticle?
}