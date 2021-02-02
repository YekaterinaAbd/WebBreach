package com.example.webbreach.domain.di

import com.example.webbreach.domain.use_case.GetBreachesUseCase
import com.example.webbreach.domain.use_case.GetLocalQuoteUseCase
import com.example.webbreach.domain.use_case.GetProcessQuoteUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetBreachesUseCase(breachRepository = get()) }
    factory { GetLocalQuoteUseCase(quoteRepository = get()) }
    factory { GetProcessQuoteUseCase(quoteRepository = get()) }
}