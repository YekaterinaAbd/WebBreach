package com.example.webbreach.view_model

import com.example.webbreach.model.Quote

sealed class QuoteState {
    data class QuoteResult(val quote: Quote) : QuoteState()
    data class Error(val message: String?) : QuoteState()
}
