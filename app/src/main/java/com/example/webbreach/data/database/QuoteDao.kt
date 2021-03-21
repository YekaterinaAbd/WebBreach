package com.example.webbreach.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.webbreach.data.model.QuoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuote(quote: QuoteEntity)

    @Query("SELECT * FROM quotes LIMIT 1")
    fun getQuote(): Flow<QuoteEntity>

    @Query("DELETE FROM quotes")
    fun deleteQuotes()
}
