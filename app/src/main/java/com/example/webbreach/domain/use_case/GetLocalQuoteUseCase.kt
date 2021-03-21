package com.example.webbreach.domain.use_case

import com.example.webbreach.domain.repository.QuoteRepository

class GetLocalQuoteUseCase(
    private val quoteRepository: QuoteRepository
) {
    suspend fun invoke() = quoteRepository.getLocalQuote()
}