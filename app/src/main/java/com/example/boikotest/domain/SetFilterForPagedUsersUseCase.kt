package com.example.boikotest.domain

import com.example.boikotest.data.user.LocalUsersRepository

class SetFilterForPagedUsersUseCase(private val localUsersRepository: LocalUsersRepository) {

    operator fun invoke(filterFavorites: Boolean) {
        localUsersRepository.setFavoritesFilter(filterFavorites)
    }
}