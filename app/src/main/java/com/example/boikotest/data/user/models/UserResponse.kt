package com.example.boikotest.data.user.models

import com.google.gson.annotations.SerializedName

data class UserResponse(@SerializedName("data") val user: UserDTO)