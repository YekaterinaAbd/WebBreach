package com.example.webbreach.repository

import com.example.webbreach.Response
import com.example.webbreach.model.SiteBreach
import com.example.webbreach.retrofit.SiteBreachApi

interface SiteBreachRepository {
    suspend fun getSiteBreaches(): Response<List<SiteBreach>>
    suspend fun getSiteBreachDetails(name: String): Response<SiteBreach>
}

class SiteBreachRepositoryImpl(
    private val api: SiteBreachApi
) : SiteBreachRepository {

    override suspend fun getSiteBreaches(): Response<List<SiteBreach>> = try {
        val response = api.getBreaches()
        if (response.isSuccessful) Response.Success(result = response.body() ?: emptyList())
        else Response.Error(response.message())
    } catch (e: Exception) {
        Response.Error(e.message.toString())
    }

    override suspend fun getSiteBreachDetails(name: String): Response<SiteBreach> = try {
        val response = api.getBreachDetails(name)
        if (response.isSuccessful && response.body() != null) Response.Success(response.body()!!)
        else Response.Error(response.message())
    } catch (e: Exception) {
        Response.Error(e.message.toString())
    }
}