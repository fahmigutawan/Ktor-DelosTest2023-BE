package com.example.data.api.article

import com.example.data.table.article.ArticleTable
import com.example.model.base.MetaResponse
import com.example.model.request.article.NewArticleRequest
import com.example.model.response.article.GetAllArticleResponse
import com.example.model.response.article.GetArticleItemResponse
import com.example.model.response.general.PaginationResponse
import com.example.wrapper.sendGeneralResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.UUID
import kotlin.math.ceil

object ArticleApi : KoinComponent {
    val articleTable by inject<ArticleTable>()

    fun Route.createNewArticle(path: String) {
        post(path) {
            val body = call.receive<NewArticleRequest>()

            transaction {
                val randomized_article_id = UUID.randomUUID()
                articleTable.insert {
                    articleTable.run {
                        it[article_id] = randomized_article_id
                        it[image] = body.image
                        it[title] = body.title
                        it[article_value] = body.article_value
                        it[author] = body.author
                        it[modified_time_inmillis] = System.currentTimeMillis()
                    }
                }
            }

            sendGeneralResponse<Any>(
                success = true,
                message = "Artikel telah ditambahkan",
                HttpStatusCode.OK
            )
        }
    }

    fun Route.getAllArticle(path: String) {
        get(path) {
            val pageSize = 5
            val page = call.parameters["page"]?.toIntOrNull() ?: 1
            val offset = (page - 1) * pageSize
            val totalArticle = transaction { articleTable.selectAll().count() }
            val totalPages = ceil(totalArticle.toDouble() / pageSize).toLong()
            val articleItems = arrayListOf<GetArticleItemResponse>()

            transaction {
                articleItems.addAll(
                    articleTable
                        .selectAll()
                        .limit(pageSize, offset.toLong())
                        .map {
                            GetArticleItemResponse(
                                article_id = it[articleTable.article_id].toString(),
                                title = it[articleTable.title],
                                article_value = it[articleTable.article_value],
                                image = it[articleTable.image],
                                modified_time_inmillis = it[articleTable.modified_time_inmillis]
                            )
                        }
                )
            }

            call.respond(
                HttpStatusCode.OK,
                GetAllArticleResponse(
                    meta = MetaResponse(
                        success = true,
                        message = "Data berhasil dimuat"
                    ),
                    data = articleItems,
                    pagination = PaginationResponse(
                        page_now = page,
                        total_page = totalPages.toInt(),
                        total_item = totalArticle.toInt()
                    )
                )
            )
        }
    }
}