@file:OptIn(ExperimentalPagingApi::class)

package com.example.boikotest.domain

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.filter
import androidx.paging.map
import com.example.boikotest.data.PAGE_SIZE
import com.example.boikotest.data.UsersRemoteMediator
import com.example.boikotest.data.favorites.FavoritesRepository
import com.example.boikotest.data.user.LocalUsersRepository
import com.example.boikotest.domain.models.toUser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class GetPagedUsersUseCase(
    private val localUsersRepository: LocalUsersRepository,
    private val remoteMediator: UsersRemoteMediator,
    private val coroutineDispatcher: CoroutineDispatcher
) {
    // fast workaround that allows us use single flow for presentation layer. I had not enough time to do it properly
    operator fun invoke() = localUsersRepository.favoritesFilterFlow.flatMapLatest { shouldFilter ->
        Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = { localUsersRepository.getUsersPagingSource() },
            remoteMediator = remoteMediator
        ).flow
            .map {
                it.map { userDTO -> userDTO.toUser() }
                    .filter { if (shouldFilter) it.isFavorite else true }
            }
    }.flowOn(coroutineDispatcher)
}