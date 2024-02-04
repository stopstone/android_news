package com.stopstone.newsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.stopstone.newsapp.R
import com.stopstone.newsapp.databinding.FragmentBookmarkBinding

class BookmarkFragment: Fragment() {
    private var _binding : FragmentBookmarkBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvBookmarkArticleList.adapter = BookmarkAdapter()

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}