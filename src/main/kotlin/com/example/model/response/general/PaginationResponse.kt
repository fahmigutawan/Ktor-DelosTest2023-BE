package com.example.model.response.general

import kotlinx.serialization.Serializable

@Serializable
data class PaginationResponse(
    val page_now:Int,
    val total_page:Int,
    val total_item:Int
)
