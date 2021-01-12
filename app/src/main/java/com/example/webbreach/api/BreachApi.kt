package com.example.webbreach.api

import com.example.webbreach.model.Breach
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BreachApi {
    @GET("breaches")
    suspend fun getBreaches(): Response<List<Breach>>

    @GET("breach/{name}")
    suspend fun getBreachDetails(
        @Path("name") name: String
    ): Response<Breach>
}