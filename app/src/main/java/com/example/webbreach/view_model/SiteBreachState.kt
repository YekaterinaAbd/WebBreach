package com.example.webbreach.view_model

import com.example.webbreach.model.SiteBreach

sealed class SiteBreachState {
    data class SiteBreachesResult(val siteBreaches: List<SiteBreach>) : SiteBreachState()
    data class Error(val message: String) : SiteBreachState()
}

sealed class SiteBreachDetailsState {
    data class SiteBreachResult(val siteBreach: SiteBreach) : SiteBreachDetailsState()
    data class Error(val message: String) : SiteBreachDetailsState()
}