package com.stopstone.newsapp.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.stopstone.newsapp.util.Constants
import com.stopstone.newsapp.data.Article
import com.stopstone.newsapp.data.Category
import com.stopstone.newsapp.databinding.FragmentCategoryArticleListBinding

class CategoryArticleListFragment : Fragment() {

    private var _binding: FragmentCategoryArticleListBinding? = null
    private val binding get() = _binding!!

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
        val items = mutableListOf<Article>()

        val category = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getSerializable(Constants.KEY_CATEGORY, Category::class.java)
        } else {
            arguments?.getSerializable(Constants.KEY_CATEGORY) as Category
        }

        val categoryPosition = arguments?.getInt(Constants.KEY_CATEGORY, 0)
        when (categoryPosition) {
            0 -> {
                repeat(5) {
                    items.add(Article("business title $it"))
                }
            }

            else -> {
                repeat(10) {
                    items.add(Article("health title $it"))
                }
            }
        }


        binding.rvCategoryArticleList.adapter = CategoryArticleAdapter(items)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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