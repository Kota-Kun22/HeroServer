package com.example.plugins


import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.install
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respondText



fun Application.configureStatusPages() {

    install(StatusPages) {
        status(HttpStatusCode.NotFound) {
         call.respondText(text=" Page Not Found", status = HttpStatusCode.NotFound )
        }


    }


}


