package com.stopstone.newsapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stopstone.newsapp.data.BookmarkItem
import com.stopstone.newsapp.data.BookmarkSectionArticle
import com.stopstone.newsapp.data.BookmarkSectionTitle
import com.stopstone.newsapp.databinding.ItemBookmarkSectionArticlesBinding
import com.stopstone.newsapp.databinding.ItemBookmarkSectionTitleBinding

class BookmarkAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = mutableListOf<BookmarkItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> BookmarkSectionTitleViewHolder.from(parent)
            else -> BookmarkSectionArticleViewHolder.from(parent)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BookmarkSectionTitleViewHolder -> {
                val item = items[position] as BookmarkSectionTitle
                holder.bind(item)
            }

            is BookmarkSectionArticleViewHolder -> {
                val item = items[position] as BookmarkSectionArticle
                holder.bind(item)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is BookmarkSectionTitle -> 0
            is BookmarkSectionArticle -> 1
        }
    }

    fun add(bookmarkArticles: List<BookmarkItem>) {
        val positionStart = items.size
        items.addAll(bookmarkArticles)
        notifyItemRangeInserted(positionStart, bookmarkArticles.size)
    }

    class BookmarkSectionTitleViewHolder(
        private val binding: ItemBookmarkSectionTitleBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BookmarkSectionTitle) {
            binding.tvBookmarkSectionTitle.text = item.label
        }

        companion object {
            fun from(parent: ViewGroup): BookmarkSectionTitleViewHolder {
                val binding = ItemBookmarkSectionTitleBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                return BookmarkSectionTitleViewHolder(binding)
            }
        }

    }


    class BookmarkSectionArticleViewHolder(private val binding: ItemBookmarkSectionArticlesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val nestedAdapter = BookmarkArticleAdapter()

        init {
            binding.rvBookmarkSectionArticleList.adapter = nestedAdapter
        }

        fun bind(item: BookmarkSectionArticle) {
            nestedAdapter.add(item.article)
        }

        companion object {
            fun from(parent: ViewGroup): BookmarkSectionArticleViewHolder {
                val binding = ItemBookmarkSectionArticlesBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                return BookmarkSectionArticleViewHolder(binding)
            }

        }
    }
}
