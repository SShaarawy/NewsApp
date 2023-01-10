package com.example.newsapp.model

data class NewsItem(
    val id: Int,
    val jetpack_featured_media_url: String,
    val excerpt: Excerpt,
    val title: Title,
)