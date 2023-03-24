package com.example.data.table.article

import org.jetbrains.exposed.sql.Table

object ArticleTable:Table("article") {
    val no = integer("no").autoIncrement()
    val article_id = uuid("article_id")
    val image = varchar("image", 255)
    val title = varchar("title", 255)
    val author = varchar("author", 255)
    val article_value = varchar("article_value", 255)
    val modified_time_inmillis = long("modified_time_inmillis")
}