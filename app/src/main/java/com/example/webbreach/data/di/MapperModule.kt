package com.example.webbreach.data.di

import com.example.webbreach.data.mapper.BreachDataMapper
import org.koin.dsl.module

val dataMapperModule = module {
    factory { BreachDataMapper() }
}