package com.example.webbreach.domain.repository

import com.example.webbreach.domain.Result
import com.example.webbreach.domain.model.Quote
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {
    suspend fun getProcessQuote(): Result<Quote>
    suspend fun getLocalQuote(): Flow<Quote>
}
