package com.example.webbreach.presentation.mapper

import com.example.webbreach.domain.Mapper
import com.example.webbreach.domain.model.Quote
import com.example.webbreach.presentation.model.QuoteModel

class QuoteModelMapper : Mapper<Quote, QuoteModel> {
    override fun from(model: Quote): QuoteModel = with(model) {
        return QuoteModel(
            quoteAuthor = quoteAuthor,
            quoteText = quoteText
        )
    }

    override fun to(model: QuoteModel): Quote = with(model) {
        return Quote(
            quoteAuthor = quoteAuthor,
            quoteText = quoteText
        )
    }
}