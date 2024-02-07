package com.stopstone.newsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.stopstone.newsapp.data.AppDatabase
import com.stopstone.newsapp.data.BookmarkArticle
import com.stopstone.newsapp.data.BookmarkItem
import com.stopstone.newsapp.data.BookmarkSectionArticle
import com.stopstone.newsapp.data.BookmarkSectionTitle
import com.stopstone.newsapp.data.sectionTitle
import com.stopstone.newsapp.databinding.FragmentBookmarkBinding
import kotlinx.coroutines.launch

class BookmarkFragment : Fragment() {
    private var _binding: FragmentBookmarkBinding? = null
    private val binding get() = _binding!!

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
        val db = activity?.applicationContext?.let { AppDatabase.getInstance(it) } ?: return
        val dao = db.bookmarkArticleDao()

        val adapter = BookmarkAdapter()
        lifecycleScope.launch {
            val articles = dao.getAllArticles()
            val items = mutableListOf<BookmarkItem>()
           articles.groupBy { it.category }
                    .forEach {
                    items.add(BookmarkSectionTitle(it.key.sectionTitle()))
                    items.add(BookmarkSectionArticle(it.value))
                }
            adapter.add(items)
        }
        binding.rvBookmarkArticleList.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}