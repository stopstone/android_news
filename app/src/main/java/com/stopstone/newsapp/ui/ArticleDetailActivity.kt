package com.stopstone.newsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import com.stopstone.newsapp.databinding.ActivityArticleDetailBinding
import com.stopstone.newsapp.ui.extensions.load

class ArticleDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArticleDetailBinding
    private val args: ArticleDetailActivityArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setLayout()

        binding.appbarArticleDetail.setNavigationOnClickListener {
            finish() // Activity에서 화면을 종료할 때
        }
    }

    private fun setLayout() {
        val article = args.article
        with(binding) {
            tvArticleDetailCategory.text = args.category.label.replaceFirstChar { it.uppercase() }
            tvArticleDetailTitle.text = article.title
            ivArticleDetailImage.load(article.urlToImage)
            tvArticleDetailContent.text = article.description
            tvArticleDetailPublishDate.text = article.publishedAt
        }

    }
}