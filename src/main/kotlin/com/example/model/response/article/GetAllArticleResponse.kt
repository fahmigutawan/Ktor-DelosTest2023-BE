package com.example.model.response.article

import com.example.model.base.MetaResponse
import com.example.model.response.general.PaginationResponse
import kotlinx.serialization.Serializable

@Serializable
data class GetAllArticleResponse(
    val meta:MetaResponse,
    val data:List<GetArticleItemResponse>,
    val pagination:PaginationResponse
)
