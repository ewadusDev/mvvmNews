package com.ewadus.mvvmnews.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ewadus.mvvmnews.data.model.news.Article
import com.ewadus.mvvmnews.data.model.news.NewsResponse
import com.ewadus.mvvmnews.data.repo.MainRepository
import com.ewadus.mvvmnews.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {


    private val _article = MutableLiveData<List<Article>>()
    val article: LiveData<List<Article>> get() = _article

    private val _messageError = MutableLiveData<String>()
    val messageError: LiveData<String> get() = _messageError



    init {

        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            val response = mainRepository.getNews()
            when (response) {
                is Resource.Success -> _article.postValue(response.data.articles)
                is Resource.Failure -> {
                    _article.postValue(null)
                    _messageError.postValue(response.msg)

                }
            }
        }
    }

}