package com.stopstone.newsapp.ui

import android.content.Context
import androidx.room.Room
import com.stopstone.newsapp.data.source.local.AppDatabase
import com.stopstone.newsapp.data.source.local.BookmarkArticleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context) : AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "news-db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideBookmarkArticleDao(appDatabase: AppDatabase): BookmarkArticleDao {
        return appDatabase.bookmarkArticleDao()
    }
}