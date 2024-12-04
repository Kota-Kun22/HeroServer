package com.example.routes

import com.example.repository.HeroRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import org.koin.ktor.ext.inject

fun Route.searchHeroes()
{
    val heroRepository : HeroRepository by inject()
    get("/boruto/heroes/search")
    {

        val name= call.request.queryParameters["name"]
        val apiResponse= heroRepository.searchHeroes(name=name)
        call.respond(message = apiResponse, status = HttpStatusCode.OK)

    }

}
