package com.example.webbreach.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.webbreach.repository.QuoteRepository
import com.example.webbreach.utils.Output
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QuoteViewModel(private val repository: QuoteRepository) : ViewModel() {

    init {
        getQuote()
    }

    private val _quoteState = MutableLiveData<QuoteState>()
    val quoteLiveData: LiveData<QuoteState> = _quoteState

    private fun getQuote() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                when (val response = repository.getLocalQuote()) {
                    is Output.Result -> _quoteState.postValue(QuoteState.QuoteResult(response.result))
                    is Output.Error -> _quoteState.postValue(QuoteState.Error(response.message))
                }
            }
        }
    }
}