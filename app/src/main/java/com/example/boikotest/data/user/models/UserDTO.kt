package com.example.boikotest.data.user.models

import com.google.gson.annotations.SerializedName

data class UserDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("email")
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("avatar")
    val avatarUrl: String,
)

fun UserDTO.toUserDB(page: Int, isFavorite: Boolean): UserDB = UserDB(
    id = id,
    email = email,
    firstName = firstName,
    lastName = lastName,
    avatarUrl = avatarUrl,
    page = page,
    isFavorite = isFavorite
)
