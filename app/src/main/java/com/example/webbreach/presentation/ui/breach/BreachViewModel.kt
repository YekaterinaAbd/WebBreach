package com.example.webbreach.presentation.ui.breach

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.webbreach.domain.Result
import com.example.webbreach.domain.use_case.GetBreachesUseCase
import com.example.webbreach.presentation.mapper.BreachModelMapper
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

class BreachViewModel(
    private val getBreachesUseCase: GetBreachesUseCase,
    private val breachModelMapper: BreachModelMapper
) : ViewModel() {

    private val _breachState = MutableLiveData<BreachState>()
    val breachLiveData: LiveData<BreachState> = _breachState

    init {
        getBreaches()
    }

    private fun getBreaches() {
        viewModelScope.launch {
            val response = withTimeout(60_000) {
                getBreachesUseCase.invoke()
            }
            when (response) {
                is Result.Success -> {
                    val breaches = response.result.map { breachModelMapper.to(it) }
                    _breachState.value = BreachState.BreachListResult(breaches)
                }
                is Result.Error -> {
                    _breachState.value = BreachState.Error(response.message)
                }
            }
        }
    }
}