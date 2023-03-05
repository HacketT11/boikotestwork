package com.example.boikotest.domain.models

import com.example.boikotest.data.user.models.UserDB
import com.example.boikotest.data.user.models.UserDTO

data class User(
    val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatarUrl: String,
    val isFavorite: Boolean
)

fun UserDTO.toUser(isFavorite: Boolean): User = User(
    id = id,
    email = email,
    firstName = firstName,
    lastName = lastName,
    avatarUrl = avatarUrl,
    isFavorite = isFavorite
)

fun UserDB.toUser(): User = User(
    id = id,
    email = email,
    firstName = firstName,
    lastName = lastName,
    avatarUrl = avatarUrl,
    isFavorite = isFavorite
)