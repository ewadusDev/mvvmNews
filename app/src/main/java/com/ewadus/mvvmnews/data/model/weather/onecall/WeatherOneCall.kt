package com.ewadus.mvvmnews.data.model.weather.onecall

data class WeatherOneCall(
    val daily: List<Daily>,
    val hourly: List<Hourly>,
    val lat: Double,
    val lon: Double,
    val minutely: List<Minutely>,
    val timezone: String,
    val timezone_offset: Int
)
