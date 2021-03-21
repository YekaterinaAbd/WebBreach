package com.example.webbreach.presentation.di

import com.example.webbreach.presentation.mapper.BreachModelMapper
import com.example.webbreach.presentation.mapper.QuoteModelMapper
import org.koin.dsl.module

val modelMapperModule = module {
    factory { BreachModelMapper() }
    factory { QuoteModelMapper() }
}