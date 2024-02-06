package com.stopstone.newsapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "bookmark_articles")
data class BookmarkArticle(
    val article: Article,
    val category: Category,
    @ColumnInfo("added_date") val addedDate: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L
}