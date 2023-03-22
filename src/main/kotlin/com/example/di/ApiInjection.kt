package com.example.di

import com.example.data.api.article.ArticleApi
import com.example.data.api.user.UserApi
import org.koin.dsl.module

object ApiInjection {
    val provide = module {
        single{UserApi}
        single{ArticleApi}
    }
}