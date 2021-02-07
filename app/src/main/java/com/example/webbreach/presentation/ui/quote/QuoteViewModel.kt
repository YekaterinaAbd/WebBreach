package com.example.webbreach.presentation.ui.quote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.webbreach.domain.use_case.GetLocalQuoteUseCase
import com.example.webbreach.presentation.mapper.QuoteModelMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QuoteViewModel(
    private val getLocalQuoteUseCase: GetLocalQuoteUseCase,
    private val quoteModelMapper: QuoteModelMapper
) : ViewModel() {

    private val _quoteState = MutableLiveData<QuoteState>()
    val quoteLiveData: LiveData<QuoteState> = _quoteState

    fun getQuote() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getLocalQuoteUseCase.invoke()
                    .catch { throwable ->
                        _quoteState.postValue(QuoteState.Error(throwable.message))
                    }
                    .map { quoteModelMapper.from(it) }
                    .collect { quoteModel ->
                        _quoteState.postValue(QuoteState.QuoteResult(quoteModel))
                    }
            }
        }
    }
}