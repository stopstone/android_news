package com.stopstone.newsapp.ui.home

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.stopstone.newsapp.data.model.Article
import com.stopstone.newsapp.data.model.Category
import com.stopstone.newsapp.databinding.FragmentCategoryArticleListBinding
import com.stopstone.newsapp.ui.common.ArticleClickListener
import com.stopstone.newsapp.util.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryArticleListFragment : Fragment(), ArticleClickListener {

    private var _binding: FragmentCategoryArticleListBinding? = null
    private val binding get() = _binding!!
    private lateinit var category: Category
    private val viewModel by viewModels<CategoryArticleViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCategory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryArticleListBinding.inflate(inflater, container, false)
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
        val action = HomeFragmentDirections.actionHomeToArticleDetail(category, article)
        findNavController().navigate(action)
    }


    private fun setCategory(): Category {
        category = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getSerializable(Constants.KEY_CATEGORY, Category::class.java)
        } else {
            arguments?.getSerializable(Constants.KEY_CATEGORY) as Category
        } ?: Category.BUSINESS
        return category
    }

    private fun setLayout() {
        val adapter = CategoryArticleAdapter(category, this)
        binding.rvCategoryArticleList.adapter = adapter

        viewModel.loadCategoryArticle(category.label)
        viewModel.items.observe(viewLifecycleOwner) { articles ->
            adapter.addArticles(articles)
        }
    }

    companion object {
        fun newInstance(category: Category): CategoryArticleListFragment {
            return CategoryArticleListFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(Constants.KEY_CATEGORY, category)
                }
            }
        }
    }

}