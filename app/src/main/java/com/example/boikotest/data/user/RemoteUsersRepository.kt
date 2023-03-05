package com.example.boikotest.data.user

import com.example.boikotest.data.user.models.PagedUsersResponse
import com.example.boikotest.data.user.models.UserDTO

interface RemoteUsersRepository {

    suspend fun loadUsers(page: Int?): Result<PagedUsersResponse>

    suspend fun loadUser(id: Int): Result<UserDTO>
}

class RemoteUsersRepositoryImpl(private val userService: UserService) : RemoteUsersRepository {

    override suspend fun loadUser(id: Int): Result<UserDTO> = userService.getUser(id).map { it.user }

    override suspend fun loadUsers(page: Int?): Result<PagedUsersResponse> =
        userService.getUsers(page)
}