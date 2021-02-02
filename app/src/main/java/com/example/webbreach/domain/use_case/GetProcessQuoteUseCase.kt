package com.example.webbreach.domain.use_case

import com.example.webbreach.domain.repository.QuoteRepository

class GetProcessQuoteUseCase(
    private val quoteRepository: QuoteRepository
) {
    suspend fun getProcessQuote() = quoteRepository.getProcessQuote()
}