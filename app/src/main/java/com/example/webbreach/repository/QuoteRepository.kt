package com.example.webbreach.repository

import com.example.webbreach.api.QuoteApi
import com.example.webbreach.database.QuoteDao
import com.example.webbreach.model.Quote
import com.example.webbreach.model.QuoteEntity
import com.example.webbreach.utils.Outcome
import com.example.webbreach.utils.Output
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface QuoteRepository {
    suspend fun processQuote(): Outcome
    suspend fun getRemoteQuote(): Output<Quote>
    suspend fun loadQuote(quote: Quote)
    suspend fun getLocalQuote(): Output<Quote>
    suspend fun deleteQuotes()
}

class QuoteRepositoryImpl(
    private val api: QuoteApi,
    private val dao: QuoteDao
) : QuoteRepository {

    override suspend fun processQuote(): Outcome = try {
        when (val response = getRemoteQuote()) {
            is Output.Result -> {
                withContext(Dispatchers.IO) {
                    deleteQuotes()
                    loadQuote(response.result)
                }
                Outcome.SUCCESS
            }
            is Output.Error -> Outcome.FAILURE
        }
    } catch (e: Exception) {
        Outcome.FAILURE
    }

    override suspend fun getRemoteQuote(): Output<Quote> = try {
        val response = api.getRandomQuote()
        if (response.isSuccessful && response.body() != null) Output.Result(response.body()!!)
        else Output.Error(response.message())
    } catch (e: Exception) {
        Output.Error(e.message)
    }

    override suspend fun loadQuote(quote: Quote) {
        if (quote.quoteText.isNullOrEmpty()) return
        val entity = QuoteEntity(quoteText = quote.quoteText, quoteAuthor = quote.quoteAuthor)
        dao.insertQuote(entity)
    }

    override suspend fun getLocalQuote(): Output<Quote> = try {
        val entity = dao.getQuote()
        Output.Result(Quote(quoteText = entity.quoteText, quoteAuthor = entity.quoteAuthor))
    } catch (e: Exception) {
        Output.Error(e.message)
    }

    override suspend fun deleteQuotes() {
        dao.deleteQuotes()
    }
}