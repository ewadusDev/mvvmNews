package com.ewadus.mvvmnews.data.api

import com.ewadus.mvvmnews.data.model.weather.current.WeatherModel
import com.ewadus.mvvmnews.util.Constants.Companion.API_KEY_WEATHER
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPIService {
    @GET("data/2.5/weather")
   suspend  fun getWeatherCurrent(
        @Query("lat")
        lat:String,
        @Query("lon")
        lon:String,
        @Query("units")
        unit:String = "metric",
        @Query("appid")
        apiKey: String = API_KEY_WEATHER
    ): Response<WeatherModel>


}