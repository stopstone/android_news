package com.stopstone.newsapp.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.stopstone.newsapp.data.model.BookmarkArticle

@Database(entities = [BookmarkArticle::class], version = 1)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookmarkArticleDao(): BookmarkArticleDao
}