package com.example.plugins

import com.example.routes.root
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        root()//first default end point
    }
}
