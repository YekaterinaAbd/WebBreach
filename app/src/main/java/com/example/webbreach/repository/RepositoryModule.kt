package com.example.webbreach.repository

import org.koin.dsl.module

val repositoryModule = module {
    single<BreachRepository> { BreachRepositoryImpl(api = get()) }
    single<QuoteRepository> { QuoteRepositoryImpl(api = get(), dao = get()) }
}