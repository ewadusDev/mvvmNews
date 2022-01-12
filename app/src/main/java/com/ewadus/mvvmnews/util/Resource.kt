package com.ewadus.mvvmnews.util

sealed class Resource<out T : Any> {
     data class Success<out T : Any>(val data: T) : Resource<T>()
     data class Failure(val msg: String) : Resource<Nothing>()


}

