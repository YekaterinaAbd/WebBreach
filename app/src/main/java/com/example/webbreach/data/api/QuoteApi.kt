package com.example.webbreach.data.api

import com.example.webbreach.data.model.QuoteData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteApi {

    @GET(".")
    suspend fun getRandomQuote(
        @Query("method") method: String = "getQuote",
        @Query("format") format: String = "json",
        @Query("lang") lang: String = "en"
    ): Response<QuoteData>
}
