package com.example.newsapp.presentation.view.fragment

import com.example.newsapp.BuildConfig

class AppleFragment: TabBaseFragmentFragment() {
    private val q = "apple"
    private val from = "2022-09-13"
    private val to = "2022-09-13"
    private val sortBy = "popularity"

    override fun callAPI() {
        newsViewModel.getAppleNews(q, from, to, sortBy, BuildConfig.API_KEY)
    }
}
