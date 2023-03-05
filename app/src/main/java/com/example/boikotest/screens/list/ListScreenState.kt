package com.example.boikotest.screens.list

import androidx.paging.PagingData
import com.example.boikotest.domain.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class ListScreenState(
    val isOnlyFavorites: Boolean = false,
    val usersPagingData: Flow<PagingData<User>> = flowOf() // just a fast workaround for single state approach
)