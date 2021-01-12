package com.example.webbreach.model

import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes")
data class QuoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 1,
    @ColumnInfo(name = "text") val quoteText: String,
    @Nullable
    @ColumnInfo(name = "author") val quoteAuthor: String?
)