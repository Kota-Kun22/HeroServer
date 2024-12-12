package com.example

import com.example.plugins.configureDefaultHeader
import com.example.plugins.configureKoin
import com.example.plugins.configureMonitoring
import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import com.example.plugins.configureStatusPages
import io.ktor.server.application.*
import org.koin.core.module.Module

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

/**
 * Configures various components and settings for the Ktor application.
 *
 * This method calls a series of setup functions to initialize key aspects of the application:
 *
 * - `configureKoin`: Sets up the Koin dependency injection framework for managing dependencies.
 * - `configureSerialization`: Establishes content negotiation and JSON serialization support.
 * - `configureMonitoring`: Enables call logging for monitoring and debugging HTTP requests.
 * - `configureRouting`: Defines API routes and static resource handling.
 * - `configureDefaultHeader`: Adds default HTTP headers, improving response caching.
 * - `configureStatusPages`: Customizes responses for specific HTTP status codes like 404.
 *
 * By combining these configurations, the application is equipped to handle requests, respond
 * with appropriate content, and integrate additional features like dependency injection, logging,
 * and error handling.
 */
fun Application.module() {
    configureKoin()
    configureSerialization()
    configureMonitoring()
    configureRouting()
    configureDefaultHeader()
    configureStatusPages()
}
