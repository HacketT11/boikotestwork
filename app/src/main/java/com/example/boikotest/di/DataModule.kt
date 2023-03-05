package com.example.boikotest.di

import androidx.paging.ExperimentalPagingApi
import androidx.room.Room
import com.example.boikotest.data.UsersRemoteMediator
import com.example.boikotest.data.db.AppDatabase
import com.example.boikotest.network.RetrofitServiceProvider
import com.example.boikotest.network.ServiceProvider
import com.example.boikotest.data.user.UserService
import com.example.boikotest.data.user.RemoteUsersRepository
import com.example.boikotest.data.user.RemoteUsersRepositoryImpl
import com.example.boikotest.data.favorites.FavoritesRepository
import com.example.boikotest.data.favorites.FavoritesRepositoryImpl
import com.example.boikotest.data.user.LocalUsersRepository
import com.example.boikotest.data.user.LocalUsersRepositoryImpl
import org.koin.dsl.bind
import org.koin.dsl.module

@OptIn(ExperimentalPagingApi::class)
val dataModule = module {
    single { Room.databaseBuilder(get(), AppDatabase::class.java, "database").build() }

    single { get<AppDatabase>().getFavoritesDao() }
    single { get<AppDatabase>().getUsersDao() }

    single { RetrofitServiceProvider() } bind ServiceProvider::class
    single { get<ServiceProvider>().provideService(UserService::class.java) }

    single { RemoteUsersRepositoryImpl(get()) } bind RemoteUsersRepository::class
    single { FavoritesRepositoryImpl(get()) } bind FavoritesRepository::class
    single { LocalUsersRepositoryImpl(get()) } bind LocalUsersRepository::class

    single { UsersRemoteMediator(get(), get(), get()) }
}