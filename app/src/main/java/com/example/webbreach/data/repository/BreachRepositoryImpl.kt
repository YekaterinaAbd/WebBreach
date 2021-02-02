package com.example.webbreach.data.repository

import com.example.webbreach.data.api.BreachApi
import com.example.webbreach.data.mapper.BreachDataMapper
import com.example.webbreach.domain.Result
import com.example.webbreach.domain.model.Breach
import com.example.webbreach.domain.repository.BreachRepository

class BreachRepositoryImpl(
    private val api: BreachApi,
    private val breachDataMapper: BreachDataMapper
) : BreachRepository {

    override suspend fun getBreaches(): Result<List<Breach>> = try {
        val response = api.getBreaches()
        if (response.isSuccessful) {
            val result = response.body()?.map { breachDataMapper.from(it) } ?: emptyList()
            Result.Success(result)
        } else Result.Error(response.message())
    } catch (e: Exception) {
        Result.Error(e.message.toString())
    }
}
