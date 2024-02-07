package com.stopstone.newsapp.ui.common

import com.stopstone.newsapp.data.model.Article
import com.stopstone.newsapp.data.model.Category

interface ArticleClickListener {
    fun onClickArticle(category: Category, article: Article)
}