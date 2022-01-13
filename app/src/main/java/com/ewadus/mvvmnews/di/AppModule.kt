package com.ewadus.mvvmnews.di

import android.content.Context
import androidx.room.Room
import com.ewadus.mvvmnews.data.api.NewsAPIService
import com.ewadus.mvvmnews.data.api.WeatherAPIService
import com.ewadus.mvvmnews.data.db.ArticleDao
import com.ewadus.mvvmnews.data.db.ArticleDatabase
import com.ewadus.mvvmnews.data.repo.MainRepository
import com.ewadus.mvvmnews.util.Constants.Companion.BASE_URL_NEWS
import com.ewadus.mvvmnews.util.Constants.Companion.BASE_URL_WEATHER
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideWeatherApiService(): WeatherAPIService =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL_WEATHER).build().create(WeatherAPIService::class.java)

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): ArticleDatabase {
        return Room.databaseBuilder(appContext,ArticleDatabase::class.java ,"article_db").build()
    }

    @Singleton
    @Provides
    fun provideArticleDao(appDatabase: ArticleDatabase): ArticleDao{
        return appDatabase.getArticleDao()
    }

    @Singleton
    @Provides
    fun provideMainRepository(
        newsAPIService: NewsAPIService,
        weatherAPIService: WeatherAPIService,
        articleDao: ArticleDao
    ) = MainRepository(newsAPIService,weatherAPIService,articleDao)

}