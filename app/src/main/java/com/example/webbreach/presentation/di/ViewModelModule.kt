package com.example.webbreach.presentation.di

import com.example.webbreach.presentation.ui.breach.BreachViewModel
import com.example.webbreach.presentation.ui.quote.QuoteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { BreachViewModel(getBreachesUseCase = get(), breachModelMapper = get()) }
    viewModel { QuoteViewModel(getLocalQuoteUseCase = get()) }
}