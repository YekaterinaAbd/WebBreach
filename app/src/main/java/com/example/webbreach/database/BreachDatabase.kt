package com.example.webbreach.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.webbreach.model.QuoteEntity

@Database(entities = [QuoteEntity::class], version = 1)
abstract class BreachDatabase : RoomDatabase() {
    abstract fun quoteDao(): QuoteDao
}