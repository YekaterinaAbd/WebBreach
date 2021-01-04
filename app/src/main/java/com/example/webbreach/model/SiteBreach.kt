package com.example.webbreach.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SiteBreach(
    @SerializedName("Name") val name: String?,
    @SerializedName("Title") val title: String?,
    @SerializedName("Domain") val domain: String?,
    @SerializedName("BreachDate") val breachDate: String?,
    @SerializedName("AddedDate") val addedDate: String?,
    @SerializedName("ModifiedDate") val modifiedDate: String?,
    @SerializedName("PwnCount") val pwnCount: Long?,
    @SerializedName("Description") val description: String?,
    @SerializedName("LogoPath") val logoPath: String?,
    @SerializedName("DataClasses") val dataClasses: List<String>?,
    @SerializedName("IsVerified") val isVerified: Boolean?,
    @SerializedName("IsFabricated") val isFabricated: Boolean?,
    @SerializedName("IsSensitive") val isSensitive: Boolean?,
    @SerializedName("IsRetired") val isRetired: Boolean?,
    @SerializedName("IsSpamList") val isSpamList: Boolean?
) : Serializable
