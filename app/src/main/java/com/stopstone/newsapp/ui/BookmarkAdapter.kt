package com.stopstone.newsapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stopstone.newsapp.data.BookmarkArticleList
import com.stopstone.newsapp.databinding.ItemBookmarkBinding

class BookmarkAdapter : RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder>() {
    private val items = mutableListOf<BookmarkArticleList>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        return BookmarkViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class BookmarkViewHolder(private val binding: ItemBookmarkBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BookmarkArticleList) {
            with(binding) {
                tvBookmarkCategoryTitle.text = item.category.label
                rvBookmarkCategoryArticleList.adapter = BookmarkArticleAdapter()
            }
        }

        companion object {
            fun from(parent: ViewGroup): BookmarkViewHolder {
                val binding = ItemBookmarkBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return BookmarkViewHolder(binding)
            }

        }
    }
}
