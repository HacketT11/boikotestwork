package com.example.boikotest.screens.user

import com.example.boikotest.domain.models.User

data class UserScreenState(
    val user: User? = null,
    val isLoading: Boolean = true
)