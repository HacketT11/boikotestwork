package com.example.boikotest.screens.user

import com.example.boikotest.domain.GetUserUseCase
import com.example.boikotest.domain.models.User
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class UserViewModelTest {

    private val getUserUseCase: GetUserUseCase = mockk()

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @Test
    fun `verify that getUserUseCase called and state changed`() {
        val user = User(
            1,
            "a",
            "b",
            "c",
            "d",
            true
        )
        coEvery { getUserUseCase.invoke(1) } returns Result.success(user)
        val userViewModel = UserViewModel(1, getUserUseCase)

        val currentState = userViewModel.state.value
        coVerify(exactly = 1) { getUserUseCase.invoke(1) }
        assertEquals(currentState.user, user)
    }

    @After
    fun after(){
        Dispatchers.resetMain()
    }
}