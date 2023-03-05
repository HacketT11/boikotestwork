package com.example.boikotest.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.boikotest.data.favorites.models.FavoriteId
import com.example.boikotest.data.user.models.UserDB
import com.example.boikotest.data.user.models.UserDTO

@Database(
    entities = [UserDB::class, FavoriteId::class],
    version = 1,
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getFavoritesDao(): FavoritesDao
    abstract fun getUsersDao(): UsersDao
}