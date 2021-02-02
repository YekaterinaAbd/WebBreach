package com.example.webbreach.presentation.ui.quote

import com.example.webbreach.presentation.model.QuoteModel

sealed class QuoteState {
    data class QuoteResult(val quote: QuoteModel) : QuoteState()
    data class Error(val message: String?) : QuoteState()
}
