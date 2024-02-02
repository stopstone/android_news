package com.stopstone.newsapp.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.stopstone.newsapp.data.Category

class HomePagerStateAdapter(fragment: Fragment, private val categories: List<Category>) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return categories.size
    }

    override fun createFragment(position: Int): Fragment {
        return CategoryArticleListFragment.newInstance(categories[position])
    }
}