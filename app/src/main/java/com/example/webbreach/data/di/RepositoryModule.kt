package com.example.webbreach.data.di

import com.example.webbreach.data.repository.BreachRepositoryImpl
import com.example.webbreach.data.repository.QuoteRepositoryImpl
import com.example.webbreach.domain.repository.BreachRepository
import com.example.webbreach.domain.repository.QuoteRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<BreachRepository> { BreachRepositoryImpl(api = get(), breachDataMapper = get()) }
    single<QuoteRepository> { QuoteRepositoryImpl(api = get(), dao = get(), quoteDataMapper = get(), quoteEntityMapper = get()) }
}