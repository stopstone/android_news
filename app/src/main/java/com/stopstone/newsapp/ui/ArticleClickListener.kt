package com.stopstone.newsapp.ui

import com.stopstone.newsapp.data.Article
import com.stopstone.newsapp.data.Category

interface ArticleClickListener {
    fun onClickArticle(category: Category, article: Article)
}