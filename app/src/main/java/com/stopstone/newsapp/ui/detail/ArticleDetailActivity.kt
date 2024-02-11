package com.stopstone.newsapp.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import com.stopstone.newsapp.data.model.Article
import com.stopstone.newsapp.data.model.Category
import com.stopstone.newsapp.databinding.ActivityArticleDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArticleDetailBinding
    private val args: ArticleDetailActivityArgs by navArgs()
    private val viewModel by viewModels<ArticleDetailViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setLayout()
    }

    private fun setLayout() {
        binding.category = args.category
        binding.article = args.article
        binding.viewModel = viewModel

        setToolbar(args.article, args.category)
    }

    private fun setToolbar(article: Article, category: Category) {
        viewModel.loadState(article, category)
        viewModel.savedState.observe(this) { savedState ->
            binding.btnArticleDetailBookmark.isSelected = savedState
        }

        binding.appbarArticleDetail.setNavigationOnClickListener {
            finish() // Activity에서 화면을 종료할 때
        }
    }
}