package com.example.boikotest.di

import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

// todo - make scoped dependencies
val mainModule = module {
    includes(dataModule, viewModelModule, domainModule)

    // can be named
    single { Dispatchers.IO }
}