package com.example.model.response.article

import kotlinx.serialization.Serializable

@Serializable
data class GetArticleItemResponse(
    val article_id:String,
    val title:String,
    val article_value:String,
    val image:String,
    val modified_time_inmillis:Long
)
