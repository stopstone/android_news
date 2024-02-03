package com.stopstone.newsapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stopstone.newsapp.data.Article
import com.stopstone.newsapp.data.Category
import com.stopstone.newsapp.databinding.ItemLatestArticleBinding
import com.stopstone.newsapp.ui.extensions.load
import com.stopstone.newsapp.ui.extensions.setPublishedAt

class LatestArticleAdapter(
    private val category: Category,
    private val listener: ArticleClickListener
) :
    RecyclerView.Adapter<LatestArticleAdapter.LatestArticleViewHolder>() {
    private val items = mutableListOf<Article>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestArticleViewHolder {
        return LatestArticleViewHolder.from(parent, listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: LatestArticleViewHolder, position: Int) {
        holder.bind(category, items[position])
    }

    fun addArticles(articles: List<Article>) {
        val positionStart = items.size
        items.addAll(articles)
        notifyItemRangeChanged(positionStart, articles.size)
    }

    class LatestArticleViewHolder(
        private val binding: ItemLatestArticleBinding,
        private val listener: ArticleClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category, article: Article) {
            itemView.setOnClickListener {
                listener.onClickArticle(category, article)
            }
            with(binding) {
                ivLatestArticleImage.load(article.urlToImage)
                tvLatestArticleTitle.text = article.title
                tvLatestArticlePublish.setPublishedAt(article.publishedAt)
            }
        }

        companion object {
            fun from(parent: ViewGroup, listener: ArticleClickListener): LatestArticleViewHolder {
                val binding = ItemLatestArticleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return LatestArticleViewHolder(binding, listener)
            }
        }

    }


}