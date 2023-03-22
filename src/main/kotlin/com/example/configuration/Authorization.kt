package com.example.configuration

import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import com.example.util.TokenManager
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

fun Application.configureAuthorization() {
    authentication {
            jwt(name = "jwt-auth") {
                jwt(name = "jwt-auth"){
                    TokenManager.configureKtorFeature(this)
                    challenge { defaultScheme, realm ->
                        call.respond(HttpStatusCode.Unauthorized, "Token has been expired. Try to re-login")
                    }
                }
            }
        }
}
