package com.ewadus.mvvmnews.data.api

import com.ewadus.mvvmnews.util.Constants.Companion.BASE_URL_NEWS
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsRetrofitInstance {
    companion object {
        private val retrofit by  lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl(BASE_URL_NEWS)
                .build()

        }
        val newAPI by lazy {
            retrofit.create(NewsAPIService::class.java)
        }
    }
}