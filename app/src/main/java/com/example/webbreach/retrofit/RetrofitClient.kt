package com.example.webbreach.retrofit

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private const val BASE_URL = "https://haveibeenpwned.com/api/v3/"

    fun getBreachApi(): SiteBreachApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttp())
            .build()
        return retrofit.create(SiteBreachApi::class.java)
    }

    private fun getOkHttp(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(getLoggingInterceptor())
        return okHttpClient.build()
    }

    private fun getLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor { message -> Log.d("OkHttp", message) }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}