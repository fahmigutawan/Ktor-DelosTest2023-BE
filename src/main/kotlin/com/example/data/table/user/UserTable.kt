package com.example.data.table.user

import org.jetbrains.exposed.sql.Table

object UserTable:Table("user") {
    val uid = uuid("uid").autoGenerate()
    val email = varchar("email", 255)
    val password = varchar("password", 512)
    val name = varchar("name", 255)
    val coin = integer("coin")
    val created_at = varchar("created_at", 255)
    val modified_at = varchar("modified_at", 255)
}