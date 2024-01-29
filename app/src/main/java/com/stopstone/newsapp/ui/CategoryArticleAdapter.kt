package com.stopstone.newsapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stopstone.newsapp.data.Article
import com.stopstone.newsapp.databinding.ItemCategoryArticleBinding

class CategoryArticleAdapter(private val items: List<Article>) : RecyclerView.Adapter<CategoryArticleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryArticleViewHolder {
        return CategoryArticleViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CategoryArticleViewHolder, position: Int) {
        holder.bind(items[position])

    }
}

class CategoryArticleViewHolder(private val binding: ItemCategoryArticleBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(article: Article) {
        binding.tvArticleTitle.text = article.title
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