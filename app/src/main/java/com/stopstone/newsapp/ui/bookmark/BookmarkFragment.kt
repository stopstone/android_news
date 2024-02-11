package com.stopstone.newsapp.ui.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.stopstone.newsapp.data.model.Article
import com.stopstone.newsapp.data.model.Category
import com.stopstone.newsapp.databinding.FragmentBookmarkBinding
import com.stopstone.newsapp.ui.common.ArticleClickListener
import com.stopstone.newsapp.ui.common.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkFragment : Fragment(),
    ArticleClickListener {
    private var _binding: FragmentBookmarkBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: BookmarkAdapter
    private val viewModel by viewModels<BookmarkArticleViewModel>()

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
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun onStart() {
        super.onStart()
        setLayout()
    }

    private fun setLayout() {
        adapter = BookmarkAdapter(this)
        binding.rvBookmarkArticleList.adapter = adapter
        observeData()
    }

    private fun observeData() {
        viewModel.loadBookmarkArticles()
        viewModel.items.observe(viewLifecycleOwner, EventObserver { articles ->
            adapter.submitBookmarkArticles(articles)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClickArticle(category: Category, article: Article) {
        val action = BookmarkFragmentDirections.actionBookmarkToArticleDetail(category, article)
        findNavController().navigate(action)
    }
}