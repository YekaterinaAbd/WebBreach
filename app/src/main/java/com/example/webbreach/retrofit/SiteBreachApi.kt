package com.example.webbreach.retrofit

import com.example.webbreach.model.SiteBreach
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SiteBreachApi {
    @GET("breaches")
    suspend fun getBreaches(): Response<List<SiteBreach>>

    @GET("breach/{name}")
    suspend fun getBreachDetails(
        @Path("name") name: String
    ): Response<SiteBreach>
}