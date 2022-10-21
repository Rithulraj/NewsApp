package com.example.newsapp.presentation.view.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.newsapp.databinding.NewsRowBinding
import com.example.newsapp.presentation.callback.ArticleClickListener
import com.example.newsapp.domain.entity.news.ArticleEntity

class NewsAdapter(private val context: Activity?,
                  private val articles: List<ArticleEntity>?,
                  private val articleClickListener: ArticleClickListener
)
    : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: NewsRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = NewsRowBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articles?.get(position)
        with(holder) {
            if (context != null) {
                Glide.with(context).load(article?.urlToImage)
                    .transform(CenterCrop(), RoundedCorners(20))
                    .into(binding.image)
            }
            binding.title.text = article?.title
            binding.author.text = article?.author
            binding.root.setOnClickListener {
                articleClickListener.onArticleClick(article)
            }
        }

    }

    override fun getItemCount(): Int {
        return articles?.size ?: 0
    }
}
