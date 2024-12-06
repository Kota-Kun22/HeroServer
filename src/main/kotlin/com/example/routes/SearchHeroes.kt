package com.example.routes

import com.example.models.ApiResponse
import com.example.repository.HeroRepository
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import kotlinx.serialization.json.Json
import org.koin.ktor.ext.inject

fun Route.searchHeroes()
{
    val heroRepository : HeroRepository by inject()
    get("/boruto/heroes/search")
    {

        val name= call.request.queryParameters["name"]
        val apiResponse= heroRepository.searchHeroes(name=name)

        val jsonResponse = Json.encodeToString(ApiResponse.serializer(), apiResponse)//encoding

        call.respondText(text = jsonResponse,status = HttpStatusCode.OK, contentType = ContentType.Application.Json )

    }

}
