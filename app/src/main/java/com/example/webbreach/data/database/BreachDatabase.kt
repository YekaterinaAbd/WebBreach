package com.example.webbreach.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.webbreach.data.model.QuoteEntity

@Database(entities = [QuoteEntity::class], version = 1)
abstract class BreachDatabase : RoomDatabase() {
    abstract fun quoteDao(): QuoteDao
}