package com.example.plugins

import com.example.routes.getAllHeroes
import com.example.routes.root
import com.example.routes.searchHeroes
import io.ktor.server.application.*
import io.ktor.server.http.content.resources
import io.ktor.server.http.content.static

import io.ktor.server.routing.*

/**
 * Configures the routing for the Ktor application.
 *
 * This function sets up several API endpoints for handling HTTP requests and serving responses.
 * The following routes are defined:
 *
 * - `root`: The default endpoint, which welcomes the user to the Hero API.
 * - `getAllHeroes`: Handles requests to fetch a paginated list of heroes.
 * - `searchHeroes`: Allows searching heroes by name.
 * - Static resources: Serves images stored in the "images" directory under the `/images` path.
 *
 * The function takes advantage of Ktor's routing capabilities to define and handle these routes,
 * enabling the application to serve dynamic and static content effectively.
 */
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
