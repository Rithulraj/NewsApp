package com.example.newsapp.presentation.view.fragment

import com.example.newsapp.BuildConfig


class TechCrunchFragment: TabBaseFragmentFragment() {
    private val source = "techcrunch"

    override fun callAPI() {
        newsViewModel.getTechCrunchNews(source, BuildConfig.API_KEY)
    }
}
