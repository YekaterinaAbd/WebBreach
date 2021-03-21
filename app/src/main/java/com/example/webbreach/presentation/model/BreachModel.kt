package com.example.webbreach.presentation.model

import java.io.Serializable

data class BreachModel(
    val name: String?,
    val title: String?,
    val domain: String?,
    val breachDate: String?,
    val addedDate: String?,
    val modifiedDate: String?,
    val pwnCount: Long?,
    val description: String?,
    val logoPath: String?,
    val dataClasses: List<String>?
) : Serializable
