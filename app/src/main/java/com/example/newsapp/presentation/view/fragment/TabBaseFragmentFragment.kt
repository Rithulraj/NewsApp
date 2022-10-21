package com.example.newsapp.presentation.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.utils.Constants
import com.example.newsapp.databinding.FragmentTabBaseBinding
import com.example.newsapp.presentation.callback.ArticleClickListener
import com.example.newsapp.domain.entity.news.ArticleEntity
import com.example.newsapp.domain.entity.news.NewsEntity
import com.example.newsapp.presentation.view.activity.WebViewActivity
import com.example.newsapp.presentation.view.adapter.NewsAdapter
import com.example.newsapp.presentation.viewmodel.NewsViewModel
import com.example.newsapp.utils.Resource
import com.example.newsapp.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
open abstract class TabBaseFragmentFragment : Fragment() {

    private var _binding: FragmentTabBaseBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var newsAdapter: NewsAdapter? = null
    private val articles: ArrayList<ArticleEntity> = arrayListOf()
    val newsViewModel: NewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =  FragmentTabBaseBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initRecyclerView()
        callAPI()
        observeData()
        return root
    }

    private fun observeData() {
        newsViewModel.mState
            .flowWithLifecycle(lifecycle,  Lifecycle.State.STARTED)
            .onEach { state -> handleStateChange(state) }
            .launchIn(lifecycleScope)
    }

    private fun updateAdapter(it: NewsEntity?) {
        if (it?.articles?.isNotEmpty() == true) {
            articles.addAll(it.articles)
            newsAdapter?.notifyDataSetChanged()
        }
    }

    private fun handleStateChange(state: Resource<NewsEntity>){
        when(state){
            is Resource.Loading -> handleProgressBar(true)
            is Resource.Success -> {
                updateAdapter(state.data)
                handleProgressBar(false)
            }
            is Resource.Error -> {
                activity?.showToast(state.message)
                handleProgressBar(false)
            }
        }
    }

    private fun handleProgressBar(show: Boolean) {
      // Handle API progress here
    }

    private fun initRecyclerView() {
        val newsRecyclerView: RecyclerView = binding.newsRecyclerview
        newsRecyclerView.layoutManager = LinearLayoutManager(activity)
        newsAdapter = NewsAdapter(activity, articles, object : ArticleClickListener {
            override fun onArticleClick(article: ArticleEntity?) {
                val intent = Intent(context, WebViewActivity::class.java)
                intent.putExtra(Constants.WEB_URL, article?.url)
                intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
                context?.startActivity(intent)
            }
        })
        newsRecyclerView.adapter = newsAdapter
    }

    abstract fun callAPI()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}