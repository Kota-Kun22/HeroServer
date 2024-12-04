package com.example

import com.example.models.ApiResponse
import com.example.plugins.configureKoin
import com.example.plugins.configureRouting
import com.example.repository.HeroRepository
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*

import kotlinx.serialization.json.Json
import org.koin.java.KoinJavaComponent.inject
import org.slf4j.LoggerFactory
import kotlin.test.*

class ApplicationTest {
    private val logger = LoggerFactory.getLogger("TestLogger")
    private val herorepository:HeroRepository by inject(HeroRepository::class.java)
    @Test
    fun testRoot() = testApplication {
        application {
            configureRouting()
        }
         client.get("/").apply {
            assertEquals(
                expected = HttpStatusCode.OK,
                actual =  status
            )
            assertEquals(
                expected =  "Welcome to my Hero API !!!",
                actual =  bodyAsText()
            )
        }
    }

    @Test
    fun `access all heroes endpoint,assert correct information`() = testApplication {
        application {
            configureKoin()
            configureRouting()
        }
        client.get("/boruto/heroes") {
            header(HttpHeaders.Accept, ContentType.Application.Json.toString())
        }.apply {
            println("Response Status: ${status}")
            assertEquals(HttpStatusCode.OK, status)
            val responseBody = bodyAsText()
            println("Response Body: $responseBody")
            assertNotNull(responseBody, "Response body should not be null")
            val actual = Json.decodeFromString<ApiResponse>(responseBody)
            val expected = ApiResponse(
                success = true,
                message = "OK",
                prevPage = 5,
                nextPage = 2,
                heroes = herorepository.page1
            )
            assertEquals(expected,actual)
        }

    }
}
