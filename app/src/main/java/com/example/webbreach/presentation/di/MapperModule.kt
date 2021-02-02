package com.example.webbreach.presentation.di

import com.example.webbreach.presentation.mapper.BreachModelMapper
import org.koin.dsl.module

val modelMapperModule = module {
    factory { BreachModelMapper() }
}