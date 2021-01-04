package com.example.webbreach.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.webbreach.Response
import com.example.webbreach.repository.SiteBreachRepository
import kotlinx.coroutines.launch

class SiteBreachDetailsViewModel(
    private val siteBreachRepository: SiteBreachRepository
) : BaseViewModel() {

    private val _siteBreachState = MutableLiveData<SiteBreachDetailsState>()
    val liveData: LiveData<SiteBreachDetailsState> = _siteBreachState

    fun getSiteBreachDetails(name: String) {
        launch {
            when (val response = siteBreachRepository.getSiteBreachDetails(name)) {
                is Response.Success -> {
                    _siteBreachState.value =
                        SiteBreachDetailsState.SiteBreachResult(response.result)
                }
                is Response.Error -> {
                    _siteBreachState.value = SiteBreachDetailsState.Error(response.message)
                }
            }
        }
    }
}