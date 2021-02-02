package com.example.webbreach.domain.use_case

import com.example.webbreach.domain.repository.BreachRepository

class GetBreachesUseCase(
    private val breachRepository: BreachRepository
) {
    suspend fun getBreaches() = breachRepository.getBreaches()
}