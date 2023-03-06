package com.example.boikotest.domain

import com.example.boikotest.data.favorites.FavoritesRepository
import com.example.boikotest.data.user.RemoteUsersRepository
import com.example.boikotest.data.user.models.UserDTO
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetUserUseCaseTest {

    private val favoriteRep: FavoritesRepository = mockk()
    private val remoteUserRep: RemoteUsersRepository = mockk()

    private val useCase = GetUserUseCase(
        favoritesRepository = favoriteRep,
        remoteUsersRepository = remoteUserRep,
        dispatcher = Dispatchers.Unconfined
    )

    @Test
    fun `test when user returns and its favorite`() = runTest {
        coEvery { favoriteRep.isFavorite(1) } returns true
        coEvery { remoteUserRep.loadUser(1) } returns Result.success(UserDTO(1, "a", "a", "a", "a"))
        val user = useCase.invoke(1).getOrNull()
        assertNotNull(user)
        assertEquals(1, user?.id)
        assertEquals("a", user?.lastName)
        assertEquals(true, user?.isFavorite)
    }

    @Test
    fun `test when user returns and its not favorite`() = runTest {
        coEvery { favoriteRep.isFavorite(1) } returns false
        coEvery { remoteUserRep.loadUser(1) } returns Result.success(UserDTO(1, "a", "a", "a", "a"))
        val user = useCase.invoke(1).getOrNull()
        assertNotNull(user)
        assertEquals(1, user?.id)
        assertEquals("a", user?.lastName)
        assertEquals(false, user?.isFavorite)
    }

    @Test
    fun `test when user request fails`() = runTest {
        coEvery { favoriteRep.isFavorite(1) } returns false
        val exception = Exception()
        coEvery { remoteUserRep.loadUser(1) } returns Result.failure(exception)
        val e = useCase.invoke(1).exceptionOrNull()
        assertNotNull(e)
        assertEquals(e, exception)
    }
}