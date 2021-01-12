package com.example.webbreach.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.webbreach.model.QuoteEntity

@Dao
interface QuoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuote(quote: QuoteEntity)

    @Query("SELECT * FROM quotes LIMIT 1")
    fun getQuote(): QuoteEntity

    @Query("DELETE FROM quotes")
    fun deleteQuotes()
}
