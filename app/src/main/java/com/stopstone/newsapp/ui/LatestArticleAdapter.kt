package com.stopstone.newsapp.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stopstone.newsapp.R
import com.stopstone.newsapp.data.Article
import com.stopstone.newsapp.databinding.FragmentLatestBinding
import com.stopstone.newsapp.databinding.ItemLatestArticleBinding
import com.stopstone.newsapp.ui.extensions.load

class LatestArticleAdapter : RecyclerView.Adapter<LatestArticleAdapter.LatestArticleViewHolder>() {
    private val items = mutableListOf<Article>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestArticleViewHolder {
        return LatestArticleViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: LatestArticleViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun addArticles(articles: List<Article>) {
        val positionStart = items.size
        items.addAll(articles)
        notifyItemRangeChanged(positionStart, articles.size)
    }

    class LatestArticleViewHolder(private val binding: ItemLatestArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            with(binding) {
                ivLatestArticleImage.load(article.urlToImage)
                tvLatestArticleTitle.text = article.title
                tvLatestArticlePublish.text = article.publishedAt
            }
        }

        companion object {
            fun from(parent: ViewGroup): LatestArticleViewHolder {
                val binding = ItemLatestArticleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return LatestArticleViewHolder(binding)
            }
        }

    }


}