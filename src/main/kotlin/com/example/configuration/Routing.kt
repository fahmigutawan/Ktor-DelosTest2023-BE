package com.example.configuration

import com.example.data.api.article.ArticleApi.createNewArticle
import com.example.data.api.article.ArticleApi.getAllArticle
import com.example.data.api.user.UserApi.login
import com.example.data.api.user.UserApi.register
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.auth.*

fun Application.configureRegularRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        login("/user/login")
        register("/user/register")
        createNewArticle("/article/new")
        getAllArticle("/article")
    }
}

fun Application.configureAuthorizedRouting(){
    routing {
        authenticate("jwt-auth") {
        }
    }
}
