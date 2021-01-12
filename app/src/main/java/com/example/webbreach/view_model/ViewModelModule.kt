package com.example.webbreach.view_model

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { BreachViewModel(repository = get()) }
    viewModel { QuoteViewModel(repository = get()) }
}