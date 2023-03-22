package com.example.model.response.user

import com.example.model.base.MetaResponse
import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    val meta: MetaResponse,
    val data: RegisterResponseData
)

@Serializable
data class RegisterResponseData(
    val email:String,
    val token:String
)
