package com.example.plugins

import com.fasterxml.jackson.databind.*
import io.ktor.serialization.jackson.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

/**
 * Configures the serialization capabilities for the Ktor application.
 *
 * This method installs the `ContentNegotiation` plugin and sets up
 * serialization support using both kotlinx.serialization's `json` and
 * Jackson's `jackson` for JSON content types. The Jackson configuration
 * is customized to enable pretty-printing of JSON output by turning on
 * the `SerializationFeature.INDENT_OUTPUT`.
 *
 * This setup allows the application to seamlessly handle JSON data
 * serialization and deserialization with different libraries, giving
 * flexibility in how JSON content is produced and consumed.
 */
fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
        jackson {
                enable(SerializationFeature.INDENT_OUTPUT)
            }

    }
}
