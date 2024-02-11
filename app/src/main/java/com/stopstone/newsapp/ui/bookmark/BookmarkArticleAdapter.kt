package com.stopstone.newsapp.ui.bookmark

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.stopstone.newsapp.data.model.BookmarkArticle
import com.stopstone.newsapp.databinding.ItemBookmarkArticleBinding
import com.stopstone.newsapp.ui.common.ArticleClickListener

class BookmarkArticleAdapter(private val listener: ArticleClickListener) :
    RecyclerView.Adapter<BookmarkArticleAdapter.BookmarkArticleViewHolder>() {
    private val items = mutableListOf<BookmarkArticle>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkArticleViewHolder {
        return BookmarkArticleViewHolder.from(parent, listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BookmarkArticleViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun add(articles: List<BookmarkArticle>) {
        val diffUtil = BookmarkArticleDiffUtil(items, articles)
        val result = DiffUtil.calculateDiff(diffUtil)
        items.clear()
        items.addAll(articles)
        result.dispatchUpdatesTo(this)
    }

    class BookmarkArticleDiffUtil(
        private val oldItems: List<BookmarkArticle>,
        private val newItems: List<BookmarkArticle>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldItems.size
        override fun getNewListSize(): Int = newItems.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldItems[oldItemPosition]
            val newItem = newItems[newItemPosition]
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItems[oldItemPosition] == newItems[newItemPosition]
        }
    }

    class BookmarkArticleViewHolder(
        private val binding: ItemBookmarkArticleBinding,
        private val listener: ArticleClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(bookmarkArticle: BookmarkArticle) {
            binding.category = bookmarkArticle.category
            binding.article = bookmarkArticle.article
            binding.listener = listener
        }

        companion object {
            fun from(parent: ViewGroup, listener: ArticleClickListener): BookmarkArticleViewHolder {
                val binding = ItemBookmarkArticleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return BookmarkArticleViewHolder(binding, listener)
            }
        }

    }
}
