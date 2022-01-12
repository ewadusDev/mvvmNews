package com.ewadus.mvvmnews.di

import com.ewadus.mvvmnews.data.api.NewsAPIService
import com.ewadus.mvvmnews.data.repo.MainRepository
import com.ewadus.mvvmnews.util.Constants.Companion.BASE_URL_NEWS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNewsApiService(): NewsAPIService =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL_NEWS)
            .build().create(NewsAPIService::class.java)

    @Singleton
    @Provides
    fun provideMainRepository(newsAPIService: NewsAPIService) = MainRepository(newsAPIService)

}