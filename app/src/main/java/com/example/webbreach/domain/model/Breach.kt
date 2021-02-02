package com.example.webbreach.domain.model

data class Breach(
    val name: String?,
    val title: String?,
    val domain: String?,
    val breachDate: String?,
    val addedDate: String?,
    val modifiedDate: String?,
    val pwnCount: Long?,
    val description: String?,
    val logoPath: String?,
    val dataClasses: List<String>?,
    val isVerified: Boolean?,
    val isFabricated: Boolean?,
    val isSensitive: Boolean?,
    val isRetired: Boolean?,
    val isSpamList: Boolean?
)