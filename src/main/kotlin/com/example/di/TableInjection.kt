package com.example.di

import com.example.data.table.user.UserTable
import org.koin.dsl.module

object TableInjection {
    val provide = module {
        single { UserTable }
        single { TableInjection }
    }
}