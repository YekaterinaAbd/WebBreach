package com.example.webbreach.data.mapper

import com.example.webbreach.data.model.QuoteEntity
import com.example.webbreach.domain.Mapper
import com.example.webbreach.domain.model.Quote

class QuoteEntityMapper : Mapper<QuoteEntity, Quote> {
    override fun from(model: QuoteEntity): Quote = with(model) {
        return Quote(
            quoteText = quoteText,
            quoteAuthor = quoteAuthor
        )
    }

    override fun to(model: Quote): QuoteEntity = with(model) {
        return QuoteEntity(
            quoteText = quoteText,
            quoteAuthor = quoteAuthor
        )
    }
}