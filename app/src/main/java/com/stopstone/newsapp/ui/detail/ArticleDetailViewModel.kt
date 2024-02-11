package com.stopstone.newsapp.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stopstone.newsapp.data.ArticleDetailRepository
import com.stopstone.newsapp.data.model.Article
import com.stopstone.newsapp.data.model.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleDetailViewModel @Inject constructor(private val repository: ArticleDetailRepository) : ViewModel() {

    private val _savedState = MutableLiveData(false)
    val savedState: MutableLiveData<Boolean> = _savedState

    fun loadState(article: Article, category: Category) {
        viewModelScope.launch {
            val bookmarkArticles = repository.getArticle(article, category)
            savedState.value = bookmarkArticles != null
        }
    }


    fun updateState(article: Article, category: Category) {
        val state = _savedState.value ?: false
        viewModelScope.launch {
            if (state) {
                repository.removeBookmarkArticle(article, category)
            } else {
                repository.addBookmarkArticle(article, category)
            }
        }
        _savedState.value = !state
    }
}