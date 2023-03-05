package com.example.boikotest.data.user

import com.example.boikotest.data.user.models.PagedUsersResponse
import com.example.boikotest.data.user.models.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {

    @GET("/api/users")
    suspend fun getUsers(@Query("page") page: Int?): Result<PagedUsersResponse>

    @GET("/api/users/{id}")
    suspend fun getUser(@Path("id") userId: Int): Result<UserResponse>
}