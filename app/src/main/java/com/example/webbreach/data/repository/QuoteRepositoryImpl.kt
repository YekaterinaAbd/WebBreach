package com.example.webbreach.data.repository

import com.example.webbreach.data.api.QuoteApi
import com.example.webbreach.data.database.QuoteDao
import com.example.webbreach.data.mapper.QuoteDataMapper
import com.example.webbreach.data.mapper.QuoteEntityMapper
import com.example.webbreach.domain.Result
import com.example.webbreach.domain.model.Quote
import com.example.webbreach.domain.repository.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class QuoteRepositoryImpl(
    private val api: QuoteApi,
    private val dao: QuoteDao,
    private val quoteDataMapper: QuoteDataMapper,
    private val quoteEntityMapper: QuoteEntityMapper
) : QuoteRepository {

    override suspend fun getProcessQuote(): Result<Quote> {
        return when (val response = getRemoteQuote()) {
            is Result.Success -> {
                withContext(Dispatchers.IO) {
                    deleteLocalQuotes()
                    loadQuote(response.result)
                }
                Result.Success(response.result)
            }
            is Result.Error -> Result.Error(response.message)
        }
    }

    override suspend fun getLocalQuote(): Flow<Quote> {
        return dao.getQuote().map { quoteEntityMapper.from(it) }
    }

    private suspend fun getRemoteQuote(): Result<Quote> = try {
        val response = api.getRandomQuote()
        if (response.isSuccessful && response.body() != null) {
            val result = quoteDataMapper.from(response.body()!!)
            Result.Success(result)
        } else Result.Error(response.message())
    } catch (e: Exception) {
        Result.Error(e.message)
    }

    private fun loadQuote(quote: Quote) {
        if (quote.quoteText.isNullOrEmpty()) return
        val entity = quoteEntityMapper.to(quote)
        dao.insertQuote(entity)
    }

    private fun deleteLocalQuotes() {
        dao.deleteQuotes()
    }
}