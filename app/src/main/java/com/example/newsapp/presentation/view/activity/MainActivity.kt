package com.example.newsapp.presentation.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.presentation.view.adapter.ViewPagerAdapter
import com.example.newsapp.presentation.view.fragment.AppleFragment
import com.example.newsapp.presentation.view.fragment.IndiaBusinessFragment
import com.example.newsapp.presentation.view.fragment.TechCrunchFragment
import com.example.newsapp.presentation.view.fragment.UsBusinessFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initTab()
    }

    private fun initTab() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        with(adapter) {
            addFragment(TechCrunchFragment(), getString(R.string.tech_crunch))
            addFragment(UsBusinessFragment(), getString(R.string.us_business))
            addFragment(IndiaBusinessFragment(), getString(R.string.india_business))
            addFragment(AppleFragment(), getString(R.string.apple))
        }
        binding.viewPager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewPager)
    }
}
