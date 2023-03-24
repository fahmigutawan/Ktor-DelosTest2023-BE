package com.example.model.request.article

import kotlinx.serialization.Serializable

@Serializable
data class NewArticleRequest(
    val title:String,
    val article_value:String,
    val author:String,
    val image:String
)
