package com.ewadus.mvvmnews.data.api

import com.ewadus.mvvmnews.data.model.news.Article
import com.ewadus.mvvmnews.data.model.news.NewsResponse
import com.ewadus.mvvmnews.util.Constants.Companion.API_KEY_NEWS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIService {

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode: String = "th",
        @Query("page")
        page: Int = 1,
        @Query("apiKey")
        apiKey:String = API_KEY_NEWS

    ): Response<NewsResponse>
}