package com.example.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.calllogging.*

/**
 * Configures monitoring for the Ktor application by installing the CallLogging feature.
 * CallLogging allows for logging HTTP call information, which aids in monitoring and debugging
 * by providing visibility into request and response details.
 */
fun Application.configureMonitoring() {
    install(CallLogging)
}
