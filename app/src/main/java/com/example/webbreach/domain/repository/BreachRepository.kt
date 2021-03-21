package com.example.webbreach.domain.repository

import com.example.webbreach.domain.Result
import com.example.webbreach.domain.model.Breach

interface BreachRepository {
    suspend fun getBreaches(): Result<List<Breach>>
}
