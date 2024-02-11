package com.stopstone.newsapp.ui.bookmark

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stopstone.newsapp.data.model.BookmarkArticle
import com.stopstone.newsapp.data.model.BookmarkItem
import com.stopstone.newsapp.data.model.BookmarkSectionArticle
import com.stopstone.newsapp.data.model.BookmarkSectionTitle
import com.stopstone.newsapp.data.model.sectionTitle
import com.stopstone.newsapp.databinding.ItemBookmarkSectionArticlesBinding
import com.stopstone.newsapp.databinding.ItemBookmarkSectionTitleBinding
import com.stopstone.newsapp.ui.common.ArticleClickListener

private const val VIEW_TYPE_SECTION_TITLE = 0
private const val VIEW_TYPE_SECTION_ARTICLE = 1

class BookmarkAdapter(private val listener: ArticleClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = mutableListOf<BookmarkItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_SECTION_TITLE -> BookmarkSectionTitleViewHolder.from(parent)
            else -> BookmarkSectionArticleViewHolder.from(parent, listener)
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
            is BookmarkSectionTitle -> VIEW_TYPE_SECTION_TITLE
            is BookmarkSectionArticle -> VIEW_TYPE_SECTION_ARTICLE
        }
    }

    fun submitBookmarkArticles(articles: List<BookmarkArticle>) {
        val items = mutableListOf<BookmarkItem>()
        articles.groupBy { it.category }
            .forEach {
                items.add(BookmarkSectionTitle(it.key.sectionTitle()))
                items.add(BookmarkSectionArticle(it.value))
            }
        add(items)
    }

    private fun add(bookmarkItems: List<BookmarkItem>) {
        items.clear()
        items.addAll(bookmarkItems)
        notifyDataSetChanged()
    }

    class BookmarkSectionTitleViewHolder(
        private val binding: ItemBookmarkSectionTitleBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BookmarkSectionTitle) {
            binding.sectionTitle = item
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


    class BookmarkSectionArticleViewHolder(
        private val binding: ItemBookmarkSectionArticlesBinding,
        private val listener: ArticleClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {
        private val nestedAdapter = BookmarkArticleAdapter(listener)

        init {
            binding.rvBookmarkSectionArticleList.adapter = nestedAdapter
        }

        fun bind(item: BookmarkSectionArticle) {
            nestedAdapter.add(item.article)
        }

        companion object {
            fun from(
                parent: ViewGroup,
                listener: ArticleClickListener
            ): BookmarkSectionArticleViewHolder {
                val binding = ItemBookmarkSectionArticlesBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                return BookmarkSectionArticleViewHolder(binding, listener)
            }
        }
    }
}
