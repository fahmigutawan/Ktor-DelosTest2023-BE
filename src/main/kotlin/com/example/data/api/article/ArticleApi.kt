package com.example.data.api.article

import com.example.data.table.article.ArticleTable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object ArticleApi: KoinComponent {
    val articleTable by inject<ArticleTable>()

}