package com.example.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.databinding.ItemNewsBinding
import com.example.newsapp.model.NewsItem

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    companion object DiffCallBack : DiffUtil.ItemCallback<NewsItem>() {
        override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,DiffCallBack)

    class NewsViewHolder(val binding: ItemNewsBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(ItemNewsBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = differ.currentList[position]

        holder.binding.tvTitle.text = news.title.rendered
        holder.binding.tvExcerpt.text = news.excerpt.rendered.substring(3,news.excerpt.rendered.length - 5)
        Glide.with(holder.itemView)
            .load(news.jetpack_featured_media_url)
            .into(holder.binding.ivPhoto)

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}