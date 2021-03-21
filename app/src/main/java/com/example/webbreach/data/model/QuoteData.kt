package com.example.webbreach.data.model

import com.google.gson.annotations.SerializedName

data class QuoteData(
    @SerializedName("quoteText") val quoteText: String?,
    @SerializedName("quoteAuthor") val quoteAuthor: String?
)

