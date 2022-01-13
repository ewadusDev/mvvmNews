package com.ewadus.mvvmnews.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ewadus.mvvmnews.data.model.news.Article
import com.ewadus.mvvmnews.data.model.weather.current.WeatherModel
import com.ewadus.mvvmnews.data.repo.MainRepository
import com.ewadus.mvvmnews.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val _articleNetwork = MutableLiveData<List<Article>>()
    val articleNetwork: LiveData<List<Article>> get() = _articleNetwork

    private val _articleSave = MutableLiveData<List<Article>>()
    val articleSave: LiveData<List<Article>> get() = _articleSave

    private val _currentWeather = MutableLiveData<WeatherModel>()
    val currentWeather: LiveData<WeatherModel> get() = _currentWeather


    private val _messageError = MutableLiveData<String>()
    val messageError: LiveData<String> get() = _messageError


    init {
        fetchArticle()
    }



      fun fetchWeather(lat: String, lon: String) {
          viewModelScope.launch {
              val response = mainRepository.getWeather(lat,lon)
              if (response.isSuccessful){
                  _currentWeather.postValue(response.body())
                  Log.i("viewmodel",_currentWeather.value.toString())
              }else{
                  _messageError.postValue(response.message())
              }
          }

    }


    private fun fetchArticle() {
        viewModelScope.launch {
            val response = mainRepository.getNews()
            when (response) {
                is Resource.Success -> _articleNetwork.postValue(response.data.articles)
                is Resource.Failure -> {
//                    _article.postValue(null)
                    _messageError.postValue(response.msg)

                }
            }
        }
    }

     fun saveNews(article: Article) {
        viewModelScope.launch {
            mainRepository.insert(article)
        }
    }

     fun deleteNews(article: Article){
        viewModelScope.launch {
            mainRepository.delete(article)
        }
    }

     fun getSaveNews() = mainRepository.getSaveNews()






}