package com.example.boikotest.domain

import com.example.boikotest.data.user.RemoteUsersRepository
import com.example.boikotest.data.favorites.FavoritesRepository
import com.example.boikotest.domain.models.User
import com.example.boikotest.domain.models.toUser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

//we can actually inject here mappers, for example UserDTO to User mappers but i'll do it for now using ext functions
class GetUserUseCase(
    private val remoteUsersRepository: RemoteUsersRepository,
    private val favoritesRepository: FavoritesRepository,
    private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(id: Int): Result<User> = withContext(dispatcher) {
        val isFavorite = favoritesRepository.isFavorite(id)
        remoteUsersRepository.loadUser(id).map { it.toUser(isFavorite) }
    }
}