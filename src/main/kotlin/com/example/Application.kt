package com.example

import com.example.data.database.DatabaseProvider
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.configuration.*
import com.example.di.ApiInjection
import com.example.di.TableInjection
import com.example.util.PasswordManager
import com.example.util.getConfig
import com.typesafe.config.ConfigFactory
import io.ktor.server.config.*
import org.koin.dsl.module
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin

fun main() {
    val config = getConfig(
        defaultEnvPath = "dev",
        hoconApplicationConfig = HoconApplicationConfig(ConfigFactory.load())
    )

    embeddedServer(
        Netty,
        port = config.port,
        host = config.host
    ) {
        module {
            install(Koin) {
                modules(
                    module {
                        single { config }
                        single { DatabaseProvider() }
                        single { PasswordManager }
                    },
                    TableInjection.provide,
                    ApiInjection.provide,
                )
            }
            configurations()
        }
    }.start(wait = true)
}

fun Application.configurations() {
    val databaseProvider by inject<DatabaseProvider>()
    databaseProvider.init()

    configureSerialization()
    configureMonitoring()
    configureHTTP()
    configureAuthorization()
    configureRegularRouting()
    configureAuthorizedRouting()
}
