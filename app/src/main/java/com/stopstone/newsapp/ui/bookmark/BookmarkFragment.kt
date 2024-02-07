package com.stopstone.newsapp.ui.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.stopstone.newsapp.NewsApplication
import com.stopstone.newsapp.data.BookmarkRepository
import com.stopstone.newsapp.data.source.local.AppDatabase
import com.stopstone.newsapp.data.model.BookmarkItem
import com.stopstone.newsapp.data.model.BookmarkSectionArticle
import com.stopstone.newsapp.data.model.BookmarkSectionTitle
import com.stopstone.newsapp.data.model.sectionTitle
import com.stopstone.newsapp.databinding.FragmentBookmarkBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class BookmarkFragment : Fragment() {
    private var _binding: FragmentBookmarkBinding? = null
    private val binding get() = _binding!!
    @Inject lateinit var repository : BookmarkRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLayout()
    }

    private fun setLayout() {
        val adapter = BookmarkAdapter()
        setAdapterItems(adapter)
        binding.rvBookmarkArticleList.adapter = adapter
    }

    private fun setAdapterItems(adapter: BookmarkAdapter) {
        lifecycleScope.launch {
            val articles = repository.getAllArticles()
            val items = mutableListOf<BookmarkItem>()
            articles.groupBy { it.category }
                .forEach {
                    items.add(BookmarkSectionTitle(it.key.sectionTitle()))
                    items.add(BookmarkSectionArticle(it.value))
                }
            adapter.add(items)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}