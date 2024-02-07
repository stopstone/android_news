package com.stopstone.newsapp.ui.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.stopstone.newsapp.data.model.Category

class HomePagerStateAdapter(fragment: Fragment, private val categories: List<Category>) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return categories.size
    }

    override fun createFragment(position: Int): Fragment {
        return CategoryArticleListFragment.newInstance(categories[position])
    }
}