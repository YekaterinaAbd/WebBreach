package com.example.webbreach.data.mapper

import com.example.webbreach.data.model.QuoteData
import com.example.webbreach.domain.Mapper
import com.example.webbreach.domain.model.Quote

class QuoteDataMapper : Mapper<QuoteData, Quote> {
    override fun from(model: QuoteData): Quote = with(model) {
        return Quote(
            quoteText = quoteText,
            quoteAuthor = quoteAuthor
        )
    }

    override fun to(model: Quote): QuoteData = with(model) {
        return QuoteData(
            quoteText = quoteText,
            quoteAuthor = quoteAuthor
        )
    }
}