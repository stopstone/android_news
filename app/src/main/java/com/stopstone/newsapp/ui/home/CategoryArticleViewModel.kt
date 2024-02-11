package com.stopstone.newsapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stopstone.newsapp.data.CategoryArticleRepository
import com.stopstone.newsapp.data.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryArticleViewModel @Inject constructor(private val repository: CategoryArticleRepository) :
    ViewModel() {
    private val _items = MutableLiveData<List<Article>>()
    val items: MutableLiveData<List<Article>> = _items

    fun loadCategoryArticle(categoryLabel: String) {
        viewModelScope.launch {
            val articles = repository.getCategoryArticle(categoryLabel)
            items.value = articles
        }
    }
}