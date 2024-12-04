package com.example.routes

import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get

fun Route.root() {

    get("/"){
        call.respond(
            message = "Welcome to my Hero API !!!",
            status = io.ktor.http.HttpStatusCode.OK
        )

    }

}