package com.example.boikotest.data.favorites.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteId(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
)