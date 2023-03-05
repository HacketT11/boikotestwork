package com.example.boikotest.data.user.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserDB(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "first_name")
    val firstName: String,
    @ColumnInfo(name = "last_name")
    val lastName: String,
    @ColumnInfo(name = "avatar")
    val avatarUrl: String,
    @ColumnInfo(name = "page")
    val page: Int,
    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean
)