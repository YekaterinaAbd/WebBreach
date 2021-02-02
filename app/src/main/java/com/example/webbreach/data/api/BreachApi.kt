package com.example.webbreach.data.api

import com.example.webbreach.data.model.BreachData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BreachApi {
    @GET("breaches")
    suspend fun getBreaches(): Response<List<BreachData>>

    @GET("breach/{name}")
    suspend fun getBreachDetails(
        @Path("name") name: String
    ): Response<BreachData>
}
