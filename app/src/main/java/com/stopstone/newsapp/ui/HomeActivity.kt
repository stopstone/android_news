package com.stopstone.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.stopstone.newsapp.R
import com.stopstone.newsapp.data.NewsService
import com.stopstone.newsapp.data.TopHeadlinesResponse
import com.stopstone.newsapp.databinding.ActivityHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = supportFragmentManager.findFragmentById(R.id.home_fragment_container)
            ?.findNavController()
        navController?.let {
            binding.bottomNavigationHome.setupWithNavController(it)
        }


        val newsService = NewsService.create()
        val result = newsService.getTopHeadLines("sports", "")
        result.enqueue(object : Callback<TopHeadlinesResponse> {
            override fun onResponse(
                call: Call<TopHeadlinesResponse>,
                response: Response<TopHeadlinesResponse>
            ) {

                if (response.isSuccessful) {
                    val topHeadlinesResponse = response.body()
                    Log.d(
                        "HomeActivity",
                        "topHeadlinesResponse : $topHeadlinesResponse"
                    )
                } else {
                    Log.d("HomeActivity", "message : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TopHeadlinesResponse>, t: Throwable) {
                Log.e("HomeActivity", "t : ${t.message}")
            }

        })
    }
}