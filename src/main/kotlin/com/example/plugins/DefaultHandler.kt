package com.example.plugins

import io.ktor.http.HttpHeaders
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.defaultheaders.DefaultHeaders
import java.time.Duration


/**
 * Configures default headers for the Ktor application.
 *
 * This method installs the `DefaultHeaders` plugin and adds a `Cache-Control` header with a
 * max age of one year and the `immutable=true` directive. This setup helps optimize caching
 * behavior by allowing clients to reuse responses and minimize repeated requests, which can
 * improve application performance and reduce server load.
 */
fun Application.configureDefaultHeader()
{
    install(DefaultHeaders){
        val oneYearInSecond= Duration.ofDays(365).seconds
        header(
            name= HttpHeaders.CacheControl,
            value = "public ,max- age= ${oneYearInSecond}, immutable=true"
        )

    }

}