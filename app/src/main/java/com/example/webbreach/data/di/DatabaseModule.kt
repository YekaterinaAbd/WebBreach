package com.example.webbreach.data.di

import android.app.Application
import androidx.room.Room
import com.example.webbreach.data.database.BreachDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(application: Application) =
        Room.databaseBuilder(
            application,
            BreachDatabase::class.java,
            "database.db"
        ).build()

    fun provideQuoteDao(database: BreachDatabase) = database.quoteDao()

    single { provideDatabase(androidApplication()) }
    single { provideQuoteDao(database = get()) }
}