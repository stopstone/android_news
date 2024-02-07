package com.stopstone.newsapp.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.navArgs
import com.stopstone.newsapp.data.ArticleDetailRepository
import com.stopstone.newsapp.data.model.Article
import com.stopstone.newsapp.data.model.Category
import com.stopstone.newsapp.data.model.sectionTitle
import com.stopstone.newsapp.databinding.ActivityArticleDetailBinding
import com.stopstone.newsapp.ui.extensions.load
import com.stopstone.newsapp.ui.extensions.setPublishedAt
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ArticleDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArticleDetailBinding
    private val args: ArticleDetailActivityArgs by navArgs()
    @Inject lateinit var repository : ArticleDetailRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setLayout()
    }

    private fun setLayout() {
        val article = args.article
        val category = args.category

        with(binding) {
            tvArticleDetailCategory.text = category.sectionTitle()
            tvArticleDetailTitle.text = article.title
            ivArticleDetailImage.load(article.urlToImage)
            tvArticleDetailContent.text = article.description
            tvArticleDetailPublishDate.setPublishedAt(article.publishedAt)
        }
        setToolbar(article, category)
    }

    private fun setToolbar(article: Article, category: Category) {
        var isAdded = false
        lifecycleScope.launch {
            val bookmarkArticle = repository.getArticle(article, category)
            isAdded = bookmarkArticle != null
            binding.btnArticleDetailBookmark.isSelected = isAdded
        }

        binding.btnArticleDetailBookmark.setOnClickListener {
            lifecycleScope.launch {
                if (isAdded) {
                    repository.removeBookmarkArticle(article, category)
                } else {
                    repository.addBookmarkArticle(args.article, args.category)
                }
                isAdded = !isAdded
                binding.btnArticleDetailBookmark.isSelected = isAdded
            }
        }
        binding.appbarArticleDetail.setNavigationOnClickListener {
            finish() // Activity에서 화면을 종료할 때
        }
    }
}