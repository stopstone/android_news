package com.stopstone.newsapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stopstone.newsapp.data.Article
import com.stopstone.newsapp.data.Category
import com.stopstone.newsapp.databinding.ItemCategoryArticleBinding
import com.stopstone.newsapp.ui.extensions.load
import com.stopstone.newsapp.ui.extensions.setPublishedAt

class CategoryArticleAdapter(
    private val category: Category,
    private val listener: ArticleClickListener
) :
    RecyclerView.Adapter<CategoryArticleViewHolder>() {
    private val items = mutableListOf<Article>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryArticleViewHolder {
        return CategoryArticleViewHolder.from(parent, listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CategoryArticleViewHolder, position: Int) {
        holder.bind(category, items[position])
    }

    fun addArticles(articles: List<Article>) {
        val positionStart = items.size
        items.addAll(articles)
        notifyItemRangeChanged(positionStart, articles.size)
    }
}

class CategoryArticleViewHolder(
    private val binding: ItemCategoryArticleBinding,
    private val listener: ArticleClickListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(category: Category, article: Article) {
        itemView.setOnClickListener {
            listener.onClickArticle(category, article)
        }
        with(binding) {
            ivArticleThumbnailImage.load(article.urlToImage)
            tvArticleTitle.text = article.title
            tvArticleDescription.text = article.description
            tvArticlePublishDate.setPublishedAt(article.publishedAt)
        }
    }

    companion object {
        fun from(parent: ViewGroup, listener: ArticleClickListener): CategoryArticleViewHolder {
            val binding = ItemCategoryArticleBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            return CategoryArticleViewHolder(binding, listener)
        }
    }
}