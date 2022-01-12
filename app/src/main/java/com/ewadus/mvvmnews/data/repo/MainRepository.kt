package com.ewadus.mvvmnews.data.repo

import com.ewadus.mvvmnews.data.api.NewsAPIService
import com.ewadus.mvvmnews.data.model.news.NewsResponse
import com.ewadus.mvvmnews.util.Resource
import java.lang.Exception
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val newsAPIService: NewsAPIService
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







}