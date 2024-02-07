package com.stopstone.newsapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark_articles")
data class BookmarkArticle(
    val article: Article,
    val category: Category,
    @ColumnInfo("added_date") val addedDate: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L
}