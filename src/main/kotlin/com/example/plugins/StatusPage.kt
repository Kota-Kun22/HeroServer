package com.example.plugins


import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.install
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respondText



/**
 * Configures custom status pages for the Ktor application.
 *
 * This function installs the `StatusPages` plugin which allows defining
 * custom responses for different HTTP status codes. Within this setup,
 * it specifically handles the `HttpStatusCode.NotFound` status by
 * responding with a "Page Not Found" message. This setup improves user
 * experience by providing meaningful error messages instead of generic
 * HTTP status responses.
 */
fun Application.configureStatusPages() {

    install(StatusPages) {
        status(HttpStatusCode.NotFound) {
         call.respondText(text=" Page Not Found", status = HttpStatusCode.NotFound )
        }


    }


}


