package com.example.newsapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.api.NewsApi.retrofitService
import com.example.newsapp.model.NewsItem
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    private val _newsList = MutableLiveData<List<NewsItem>>()
    val newsList: LiveData<List<NewsItem>> = _newsList

    init {
        getNewsList()
    }

    private fun getNewsList() {
        viewModelScope.launch {
            val response = retrofitService.getNews()
            if(response.isSuccessful){
                _newsList.value = response.body()
            }
        }
    }
}