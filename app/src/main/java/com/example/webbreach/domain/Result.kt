package com.example.webbreach.domain

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val result: T) : Result<T>()
    data class Error(val message: String?) : Result<Nothing>()
}
