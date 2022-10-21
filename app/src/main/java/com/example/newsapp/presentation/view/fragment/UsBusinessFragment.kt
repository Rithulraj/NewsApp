package com.example.newsapp.presentation.view.fragment

import com.example.newsapp.BuildConfig

class UsBusinessFragment: TabBaseFragmentFragment() {
    private val country = "us"
    private val category = "business"

    override fun callAPI() {
        newsViewModel.getBusinessNews(country, category, BuildConfig.API_KEY)
    }
}
