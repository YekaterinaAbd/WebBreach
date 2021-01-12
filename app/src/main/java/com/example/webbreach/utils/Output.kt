package com.example.webbreach.utils

sealed class Output<out T : Any> {
    data class Result<out T : Any>(val result: T) : Output<T>()
    data class Error(val message: String?) : Output<Nothing>()
}
