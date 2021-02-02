package com.example.webbreach.data.repository

import com.example.webbreach.data.api.QuoteApi
import com.example.webbreach.data.database.QuoteDao
import com.example.webbreach.data.model.QuoteEntity
import com.example.webbreach.domain.Result
import com.example.webbreach.domain.model.Quote
import com.example.webbreach.domain.repository.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuoteRepositoryImpl(
    private val api: QuoteApi,
    private val dao: QuoteDao
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

    override suspend fun getLocalQuote(): Quote {
        val entity = dao.getQuote()
        return Quote(quoteText = entity.quoteText, quoteAuthor = entity.quoteAuthor)
    }

    private suspend fun getRemoteQuote(): Result<Quote> = try {
        val response = api.getRandomQuote()
        if (response.isSuccessful && response.body() != null) {
            val quote = response.body()
            val result = Quote(quoteText = quote?.quoteText, quoteAuthor = quote?.quoteAuthor)
            Result.Success(result)
        } else Result.Error(response.message())
    } catch (e: Exception) {
        Result.Error(e.message)
    }

    private fun loadQuote(quote: Quote) {
        if (quote.quoteText.isNullOrEmpty()) return
        val entity = QuoteEntity(quoteText = quote.quoteText, quoteAuthor = quote.quoteAuthor)
        dao.insertQuote(entity)
    }

    private fun deleteLocalQuotes() {
        dao.deleteQuotes()
    }
}