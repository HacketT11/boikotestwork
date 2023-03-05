package com.example.boikotest.domain

import com.example.boikotest.data.favorites.FavoritesRepository
import com.example.boikotest.data.user.LocalUsersRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class SetFavoriteUseCase(
    private val favoritesRepository: FavoritesRepository,
    private val localUsersRepository: LocalUsersRepository,
    private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(userId: Int, isFavorite: Boolean) = withContext(dispatcher){
        if (isFavorite)
            favoritesRepository.addFavorite(userId)
        else
            favoritesRepository.removeFavorite(userId)
        localUsersRepository.updateFavoriteByUserId(userId, isFavorite)
    }
}