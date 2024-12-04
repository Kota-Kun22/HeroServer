package com.example.plugins

import com.example.routes.getAllHeroes
import com.example.routes.root
import com.example.routes.searchHeroes
import io.ktor.server.application.*
import io.ktor.server.http.content.resources
import io.ktor.server.http.content.static

import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        root()//first default end point
        getAllHeroes()
        searchHeroes()
        static("/images"){
            resources("images")
        }
    }
}
