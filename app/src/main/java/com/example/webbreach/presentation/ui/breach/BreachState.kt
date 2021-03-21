package com.example.webbreach.presentation.ui.breach

import com.example.webbreach.presentation.model.BreachModel

sealed class BreachState {
    data class BreachListResult(val list: List<BreachModel>) : BreachState()
    data class Error(val message: String?) : BreachState()
}
