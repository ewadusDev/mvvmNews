package com.ewadus.mvvmnews.data.model.weather.current

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)