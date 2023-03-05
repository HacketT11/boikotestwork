package com.example.boikotest.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.boikotest.data.favorites.models.FavoriteId
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {

    @Insert
    suspend fun insert(favoriteId: FavoriteId)

    @Query("SELECT * FROM favorites")
    suspend fun getFavorites(): List<FavoriteId>

    @Delete
    suspend fun delete(favoriteId: FavoriteId)

    @Query("SELECT EXISTS(SELECT * FROM favorites WHERE id = :id)")
    suspend fun isExist(id: Int): Boolean
}