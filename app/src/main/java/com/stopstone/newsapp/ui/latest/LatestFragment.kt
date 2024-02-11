package com.stopstone.newsapp.ui.latest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.stopstone.newsapp.data.model.Article
import com.stopstone.newsapp.data.model.Category
import com.stopstone.newsapp.databinding.FragmentLatestBinding
import com.stopstone.newsapp.ui.common.ArticleClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LatestFragment() : Fragment(), ArticleClickListener {

    private var _binding: FragmentLatestBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<LatestArticleViewModel>()

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
        binding.lifecycleOwner = viewLifecycleOwner
        setLayout()
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

    private fun setLayout() {
        val adapter = LatestArticleAdapter(Category.DEFAULT, this)
        binding.rvLatestArticleList.adapter = adapter

        viewModel.items.observe(viewLifecycleOwner) { articles ->
            adapter.addArticles(articles)
        }
    }
}