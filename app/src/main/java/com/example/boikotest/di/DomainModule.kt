@file:OptIn(ExperimentalPagingApi::class)

package com.example.boikotest.di

import androidx.paging.ExperimentalPagingApi
import com.example.boikotest.domain.GetPagedUsersUseCase
import com.example.boikotest.domain.GetUserUseCase
import com.example.boikotest.domain.SetFavoriteUseCase
import com.example.boikotest.domain.SetFilterForPagedUsersUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetUserUseCase(get(), get(), get()) }
    single { GetPagedUsersUseCase(get(), get(), get()) }
    single { SetFavoriteUseCase(get(), get(), get()) }
    single { SetFilterForPagedUsersUseCase(get()) }
}