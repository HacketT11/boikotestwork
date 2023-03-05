package com.example.boikotest.data.favorites

import com.example.boikotest.data.db.FavoritesDao
import com.example.boikotest.data.favorites.models.FavoriteId
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    suspend fun getFavorites(): List<FavoriteId>

    suspend fun addFavorite(userId: Int)

    suspend fun removeFavorite(userId: Int)

    suspend fun isFavorite(userId: Int): Boolean
}

class FavoritesRepositoryImpl(private val favoritesDao: FavoritesDao) : FavoritesRepository {

    override suspend fun addFavorite(userId: Int) = favoritesDao.insert(FavoriteId(userId))

    override suspend fun getFavorites(): List<FavoriteId> = favoritesDao.getFavorites()

    override suspend fun removeFavorite(userId: Int) = favoritesDao.delete(FavoriteId(userId))

    override suspend fun isFavorite(userId: Int): Boolean = favoritesDao.isExist(userId)
}