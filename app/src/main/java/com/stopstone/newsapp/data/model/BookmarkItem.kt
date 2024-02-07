package com.stopstone.newsapp.data.model

sealed class BookmarkItem
data class BookmarkSectionTitle(
    val label: String,
) : BookmarkItem()

data class BookmarkSectionArticle(
    val article: List<BookmarkArticle>,
) : BookmarkItem()