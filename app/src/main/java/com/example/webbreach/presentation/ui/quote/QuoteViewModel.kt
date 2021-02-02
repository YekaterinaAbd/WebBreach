package com.example.webbreach.presentation.ui.quote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.webbreach.domain.use_case.GetLocalQuoteUseCase
import com.example.webbreach.presentation.model.QuoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QuoteViewModel(
    private val getLocalQuoteUseCase: GetLocalQuoteUseCase
) : ViewModel() {

    init {
        getQuote()
    }

    private val _quoteState = MutableLiveData<QuoteState>()
    val quoteLiveData: LiveData<QuoteState> = _quoteState

    private fun getQuote() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = getLocalQuoteUseCase.getLocalQuote()
                val quote = QuoteModel(response.quoteText, response.quoteAuthor)
                _quoteState.postValue(QuoteState.QuoteResult(quote))
            }
        }
    }
}