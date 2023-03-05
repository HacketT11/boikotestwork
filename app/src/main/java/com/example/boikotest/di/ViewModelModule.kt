package com.example.boikotest.di

import com.example.boikotest.screens.list.ListViewModel
import com.example.boikotest.screens.user.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::ListViewModel)
    viewModel { params -> UserViewModel(params.get(), get()) }
}