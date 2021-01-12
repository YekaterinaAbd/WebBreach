package com.example.webbreach.repository

import com.example.webbreach.api.BreachApi
import com.example.webbreach.model.Breach
import com.example.webbreach.utils.Output

interface BreachRepository {
    suspend fun getBreaches(): Output<List<Breach>>
    suspend fun getBreachDetails(name: String): Output<Breach>
}

class BreachRepositoryImpl(
    private val api: BreachApi
) : BreachRepository {

    override suspend fun getBreaches(): Output<List<Breach>> = try {
        val response = api.getBreaches()
        if (response.isSuccessful) Output.Result(result = response.body() ?: emptyList())
        else Output.Error(response.message())
    } catch (e: Exception) {
        Output.Error(e.message.toString())
    }

    override suspend fun getBreachDetails(name: String): Output<Breach> = try {
        val response = api.getBreachDetails(name)
        if (response.isSuccessful && response.body() != null) Output.Result(response.body()!!)
        else Output.Error(response.message())
    } catch (e: Exception) {
        Output.Error(e.message.toString())
    }
}
