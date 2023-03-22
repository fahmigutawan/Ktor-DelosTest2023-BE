package com.example.model.response.user

import com.example.model.base.MetaResponse
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val meta: MetaResponse,
    val data: UserDataResponse
)

@Serializable
data class UserDataResponse(
    val email:String,
    val name:String,
    val role_name:String,
    val created_at:String,
    val modified_at:String?
)
