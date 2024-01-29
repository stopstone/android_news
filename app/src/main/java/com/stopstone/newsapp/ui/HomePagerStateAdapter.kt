package com.stopstone.newsapp.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.stopstone.newsapp.data.Category
import com.stopstone.newsapp.ui.CategoryArticleListFragment

class HomePagerStateAdapter(fragment: Fragment, private val categories: Array<Category>) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return categories.size
    }

    override fun createFragment(position: Int): Fragment {
        return CategoryArticleListFragment.newInstance(categories[position])
    }
}