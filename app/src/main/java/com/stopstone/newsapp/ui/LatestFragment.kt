package com.stopstone.newsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.stopstone.newsapp.data.Article
import com.stopstone.newsapp.data.Category
import com.stopstone.newsapp.data.NewsService
import com.stopstone.newsapp.databinding.FragmentLatestBinding
import kotlinx.coroutines.launch

class LatestFragment() : Fragment(), ArticleClickListener {

    private var _binding: FragmentLatestBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLatestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = LatestArticleAdapter(Category.DEFAULT, this)
        binding.rvLatestArticleList.adapter = adapter
        lifecycleScope.launch {
            val newsService = NewsService.create()
            val result = newsService.getTopHeadLines()
            adapter.addArticles(result.articles)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClickArticle(category: Category, article: Article) {
        val action =
            LatestFragmentDirections.actionLatestArticlesToArticleDetail(category, article)
        findNavController().navigate(action)
    }
}