package com.example.boikotest.data.user.models

import com.google.gson.annotations.SerializedName

data class PagedUsersResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("total") val count: Int,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("data") val users: List<UserDTO>
)
