package com.stopstone.newsapp.ui.latest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stopstone.newsapp.data.LatestArticleRepository
import com.stopstone.newsapp.data.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LatestArticleViewModel @Inject constructor(private val repository: LatestArticleRepository) :
    ViewModel() {
    init {
        loadArticle()
    }
    private val _items = MutableLiveData<List<Article>>()
    val items: LiveData<List<Article>> = _items

    fun loadArticle() {
        viewModelScope.launch {
            val articles = repository.getLatestArticle()
            _items.value = articles
        }

    }
}