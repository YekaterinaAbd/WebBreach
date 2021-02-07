package com.example.webbreach.data.di

import com.example.webbreach.data.mapper.BreachDataMapper
import com.example.webbreach.data.mapper.QuoteDataMapper
import com.example.webbreach.data.mapper.QuoteEntityMapper
import org.koin.dsl.module

val dataMapperModule = module {
    factory { BreachDataMapper() }
    factory { QuoteDataMapper() }
    factory { QuoteEntityMapper() }
}