package com.stopstone.newsapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.navArgs
import com.stopstone.newsapp.data.AppDatabase
import com.stopstone.newsapp.data.BookmarkArticle
import com.stopstone.newsapp.databinding.ActivityArticleDetailBinding
import com.stopstone.newsapp.ui.extensions.load
import com.stopstone.newsapp.ui.extensions.setPublishedAt
import com.stopstone.newsapp.util.DateFormatText
import kotlinx.coroutines.launch

class ArticleDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArticleDetailBinding
    private val args: ArticleDetailActivityArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setLayout()

        val db = AppDatabase.getInstance(applicationContext)
        val dao = db.bookmarkArticleDao()
        var isAdded = false
        lifecycleScope.launch {
            val bookmarkArticle = dao.getArticle(args.article, args.category)
            isAdded = bookmarkArticle != null
            binding.btnArticleDetailBookmark.isSelected = isAdded
        }

        binding.btnArticleDetailBookmark.setOnClickListener {
            lifecycleScope.launch {
                if (isAdded) {
                    dao.delete(args.article, args.category)
                } else {
                    dao.insert(
                        BookmarkArticle(
                            args.article,
                            args.category,
                            DateFormatText.getCurrentDate()
                        )
                    )
                }
                isAdded = !isAdded
                binding.btnArticleDetailBookmark.isSelected = isAdded
            }
        }
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
            tvArticleDetailPublishDate.setPublishedAt(article.publishedAt)
        }
    }
}