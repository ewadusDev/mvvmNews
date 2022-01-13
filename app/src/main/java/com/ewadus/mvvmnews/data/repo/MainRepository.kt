package com.ewadus.mvvmnews.data.repo

import com.ewadus.mvvmnews.data.api.NewsAPIService
import com.ewadus.mvvmnews.data.api.WeatherAPIService
import com.ewadus.mvvmnews.data.db.ArticleDao
import com.ewadus.mvvmnews.data.db.ArticleDatabase
import com.ewadus.mvvmnews.data.model.news.Article
import com.ewadus.mvvmnews.data.model.news.NewsResponse
import com.ewadus.mvvmnews.util.Resource
import java.lang.Exception
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val newsAPIService: NewsAPIService,
    private val weatherPIService: WeatherAPIService,
    private val articleDao: ArticleDao
) {

    suspend fun getNews(): Resource<NewsResponse> {
        return try {
            val response = newsAPIService.getBreakingNews()
            if (response.isSuccessful) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Failure("Error News response")
            }

        } catch (e: Exception) {
            Resource.Failure(e.message.toString())
        }

    }

    suspend fun getWeather(lat: String, lon: String) = weatherPIService.getWeatherCurrent(lat, lon)


    suspend fun insert(article: Article): Long {
        return articleDao.insert(article)
    }

    fun getSaveNews() = articleDao.getAllArticle()


    suspend fun delete(article: Article) {
        return articleDao.deleteArticle(article)
    }


}