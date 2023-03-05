package com.example.boikotest.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.boikotest.data.favorites.FavoritesRepository
import com.example.boikotest.data.user.LocalUsersRepository
import com.example.boikotest.data.user.RemoteUsersRepository
import com.example.boikotest.data.user.models.UserDB
import com.example.boikotest.data.user.models.UserDTO
import com.example.boikotest.data.user.models.toUserDB
import retrofit2.HttpException
import java.io.IOException

const val PAGE_SIZE = 6

@ExperimentalPagingApi
class UsersRemoteMediator(
    private val remoteUsersRepository: RemoteUsersRepository,
    private val localUsersRepository: LocalUsersRepository,
    private val favoritesRepository: FavoritesRepository
) : RemoteMediator<Int, UserDB>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UserDB>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND ->
                return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull()
                    ?: return MediatorResult.Success(
                        endOfPaginationReached = true
                    )
                lastItem.page + 1
            }
        }


        return try {
            val apiResponse = remoteUsersRepository.loadUsers(page = page)

            val users = apiResponse.getOrThrow()
            val endOfPaginationReached = users.page == users.totalPages
            val favorites = favoritesRepository.getFavorites()

            localUsersRepository.insertUsers(users.users.map { user ->
                val isFavorite = favorites.find { it.id == user.id } != null
                user.toUserDB(users.page, isFavorite)
            })

            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (error: IOException) {
            MediatorResult.Error(error)
        } catch (error: HttpException) {
            MediatorResult.Error(error)
        }
    }
}