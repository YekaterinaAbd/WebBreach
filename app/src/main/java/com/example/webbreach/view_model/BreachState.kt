package com.example.webbreach.view_model

import com.example.webbreach.model.Breach

sealed class BreachState {
    data class BreachListResult(val list: List<Breach>) : BreachState()
    data class Error(val message: String?) : BreachState()
}
