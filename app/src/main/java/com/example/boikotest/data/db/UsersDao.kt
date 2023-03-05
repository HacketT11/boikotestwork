package com.example.boikotest.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.boikotest.data.user.models.UserDB

//initially idea was to link two tables and using users dao get paging data with filter already
@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<UserDB>)

    @Query("Select * From users Order By page")
    fun getUsers(): PagingSource<Int, UserDB>

    @Query("UPDATE users SET is_favorite = :isFavorite where id = :userId")
    fun updateFavoriteById(userId: Int, isFavorite: Boolean)
}