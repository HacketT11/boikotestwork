package com.example.boikotest.data.user

import androidx.paging.PagingSource
import com.example.boikotest.data.db.UsersDao
import com.example.boikotest.data.user.models.UserDB
import com.example.boikotest.data.user.models.UserDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

interface LocalUsersRepository {

    val favoritesFilterFlow: Flow<Boolean>

    fun setFavoritesFilter(shouldFilterFavorites: Boolean)

    fun getUsersPagingSource(): PagingSource<Int, UserDB>

    suspend fun insertUsers(users: List<UserDB>)

    suspend fun updateFavoriteByUserId(userId: Int, isFavorite: Boolean)
}

class LocalUsersRepositoryImpl(private val usersDao: UsersDao) : LocalUsersRepository {

    private val _favoritesFilterFlow = MutableStateFlow(false)

    override val favoritesFilterFlow: Flow<Boolean>
        get() = _favoritesFilterFlow.asStateFlow()

    override fun setFavoritesFilter(shouldFilterFavorites: Boolean) {
        _favoritesFilterFlow.value = shouldFilterFavorites
    }

    override fun getUsersPagingSource(): PagingSource<Int, UserDB> = usersDao.getUsers()

    override suspend fun insertUsers(users: List<UserDB>) = usersDao.insertAll(users)

    override suspend fun updateFavoriteByUserId(userId: Int, isFavorite: Boolean) =
        usersDao.updateFavoriteById(userId, isFavorite)
}