package com.stopstone.newsapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stopstone.newsapp.R
import com.stopstone.newsapp.data.Article
import com.stopstone.newsapp.databinding.ItemCategoryArticleBinding
import com.stopstone.newsapp.ui.extensions.load

class CategoryArticleAdapter : RecyclerView.Adapter<CategoryArticleViewHolder>() {
    private val items = mutableListOf<Article>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryArticleViewHolder {
        return CategoryArticleViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CategoryArticleViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun addArticles(articles: List<Article>) {
        val positionStart = items.size
        items.addAll(articles)
        notifyItemRangeChanged(positionStart, articles.size)
    }
}

class CategoryArticleViewHolder(private val binding: ItemCategoryArticleBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(article: Article) {
        with(binding) {
            ivArticleThumbnailImage.load(article.urlToImage)
            tvArticleTitle.text = article.title
            tvArticleDescription.text = article.description
            tvArticlePublishDate.text = article.publishedAt
        }
    }

    companion object {
        fun from(parent: ViewGroup): CategoryArticleViewHolder {
            val binding = ItemCategoryArticleBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            return CategoryArticleViewHolder(binding)
        }
    }
}