package com.stopstone.newsapp.ui.bookmark

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stopstone.newsapp.data.BookmarkRepository
import com.stopstone.newsapp.data.model.BookmarkArticle
import com.stopstone.newsapp.ui.common.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkArticleViewModel @Inject constructor(private val repository: BookmarkRepository) :
    ViewModel() {
    private val _items = MutableLiveData<Event<List<BookmarkArticle>>>()
    val items: MutableLiveData<Event<List<BookmarkArticle>>> = _items

    fun loadBookmarkArticles() {
        viewModelScope.launch {
            val articles = repository.getAllArticles()
            _items.value = Event(articles)
        }
    }
}