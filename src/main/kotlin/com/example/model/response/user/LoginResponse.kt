package com.example.model.response.user

import com.example.model.base.MetaResponse
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val meta: MetaResponse,
    val data:LoginResponseData
)

@Serializable
data class LoginResponseData(
    val name:String,
    val email:String,
    val token:String
)
