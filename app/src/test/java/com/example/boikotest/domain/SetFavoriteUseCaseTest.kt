package com.example.boikotest.domain

import com.example.boikotest.data.favorites.FavoritesRepository
import com.example.boikotest.data.user.LocalUsersRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.Test

class SetFavoriteUseCaseTest {

    private val favoritesRepository: FavoritesRepository = mockk(relaxed = true)
    private val localUserRepository: LocalUsersRepository = mockk(relaxed = true)
    private val useCase = SetFavoriteUseCase(
        dispatcher = Dispatchers.Unconfined,
        localUsersRepository = localUserRepository,
        favoritesRepository = favoritesRepository
    )


    @Test
    fun `test when set user as favorite`() = runTest {
        useCase.invoke(1, true)
        coVerify(exactly = 1) { localUserRepository.updateFavoriteByUserId(1, true) }
        coVerify(exactly = 1) { favoritesRepository.addFavorite(1) }
    }

    @Test
    fun `test when set user as not favorite`() = runTest {
        useCase.invoke(1, false)
        coVerify(exactly = 1) { localUserRepository.updateFavoriteByUserId(1, false) }
        coVerify(exactly = 1) { favoritesRepository.removeFavorite(1) }
    }
}