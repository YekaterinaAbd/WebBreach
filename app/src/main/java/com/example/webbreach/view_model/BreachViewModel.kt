package com.example.webbreach.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.webbreach.repository.BreachRepository
import com.example.webbreach.utils.Output
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

class BreachViewModel(private val repository: BreachRepository) : ViewModel() {

    private val _breachState = MutableLiveData<BreachState>()
    val breachLiveData: LiveData<BreachState> = _breachState

    init {
        getBreaches()
    }

    private fun getBreaches() {
        viewModelScope.launch {
            val response = withTimeout(60_000) {
                repository.getBreaches()
            }
            when (response) {
                is Output.Result -> {
                    _breachState.value = BreachState.BreachListResult(response.result)
                }
                is Output.Error -> {
                    _breachState.value = BreachState.Error(response.message)
                }
            }
        }
    }
}