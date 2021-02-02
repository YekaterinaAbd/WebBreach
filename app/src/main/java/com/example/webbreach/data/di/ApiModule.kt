package com.example.webbreach.data.di

import com.example.webbreach.data.api.BreachApi
import com.example.webbreach.data.api.QuoteApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

private const val BREACH_BASE_URL = "https://haveibeenpwned.com/api/v3/"
private const val QUOTES_BASE_URL = "https://api.forismatic.com/api/1.0/"

val apiModule = module {

    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor { message -> Timber.tag("OkHttp").d(message) }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    fun provideOkHttp(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(provideLoggingInterceptor())
        return okHttpClient.build()
    }

    fun provideBreachApi(): BreachApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BREACH_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttp())
            .build()
        return retrofit.create(BreachApi::class.java)
    }

    fun provideQuoteApi(): QuoteApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(QUOTES_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttp())
            .build()
        return retrofit.create(QuoteApi::class.java)
    }

    single { provideBreachApi() }
    single { provideQuoteApi() }
}