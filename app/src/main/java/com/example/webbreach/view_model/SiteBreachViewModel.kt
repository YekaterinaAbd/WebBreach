package com.example.webbreach.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.webbreach.Response
import com.example.webbreach.repository.SiteBreachRepository
import kotlinx.coroutines.launch

class SiteBreachViewModel(
    private val siteBreachRepository: SiteBreachRepository
) : BaseViewModel() {

    private val _siteBreachState = MutableLiveData<SiteBreachState>()
    val liveData: LiveData<SiteBreachState> = _siteBreachState

    init {
        getSiteBreaches()
    }

    private fun getSiteBreaches() {
        launch {
            when (val response = siteBreachRepository.getSiteBreaches()) {
                is Response.Success -> {
                    _siteBreachState.value = SiteBreachState.SiteBreachesResult(response.result)
                }
                is Response.Error -> {
                    _siteBreachState.value = SiteBreachState.Error(response.message)
                }
            }
        }
    }
}