package com.example.newsapp.presentation.view.fragment

import com.example.newsapp.BuildConfig

class IndiaBusinessFragment: TabBaseFragmentFragment() {
    private val country = "in"
    private val category = "business"

    override fun callAPI() {
        newsViewModel.getBusinessNews(country, category, BuildConfig.API_KEY)
    }
}
