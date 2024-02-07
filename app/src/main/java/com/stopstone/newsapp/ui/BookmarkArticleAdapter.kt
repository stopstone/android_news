package com.stopstone.newsapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stopstone.newsapp.data.Article
import com.stopstone.newsapp.data.BookmarkArticle
import com.stopstone.newsapp.databinding.ItemBookmarkArticleBinding
import com.stopstone.newsapp.ui.extensions.load

class BookmarkArticleAdapter :
    RecyclerView.Adapter<BookmarkArticleAdapter.BookmarkArticleViewHolder>() {
    private val items = mutableListOf<BookmarkArticle>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkArticleViewHolder {
        return BookmarkArticleViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BookmarkArticleViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun add(articles: List<BookmarkArticle>) {
        val positionStart = items.size
        items.addAll(articles)
        notifyItemRangeInserted(positionStart, articles.size)
    }

    class BookmarkArticleViewHolder(private val binding: ItemBookmarkArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(bookmarkArticle: BookmarkArticle) {
            val article = bookmarkArticle.article
            with(binding) {
                ivBookmarkArticleImage.load(article.urlToImage)
                tvBookmarkArticleTitle.text = article.title
                tvBookmarkArticlePublish.text = article.publishedAt
            }
        }

        companion object {
            fun from(parent: ViewGroup): BookmarkArticleViewHolder {
                val binding = ItemBookmarkArticleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return BookmarkArticleViewHolder(binding)
            }
        }

    }
}
