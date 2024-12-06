package com.example

import com.example.di.koinModule
import com.example.models.ApiResponse
import com.example.plugins.configureRouting
import com.example.repository.HeroRepository
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.json.Json
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.GlobalContext.stopKoin
import org.koin.java.KoinJavaComponent.inject
import kotlin.test.*

class ApplicationTest {
//    private val logger = LoggerFactory.getLogger("TestLogger")
    private val heroesRepository:HeroRepository by inject(HeroRepository::class.java)

    @BeforeTest
    fun setupKoin() {
        stopKoin() // Stop any existing Koin instance
        startKoin {
            modules(koinModule)
        }
    }

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
            //configureKoin()
            configureRouting()
        }
        client.get("/boruto/heroes") {
        }.apply {
//            println("Response Status: ${status}")

            assertEquals(expected = HttpStatusCode.OK,  actual = status)

            println("Response Status: ${status}")
            val responseBody = bodyAsText()

            println("Response Body: $responseBody")

            assertNotNull(responseBody, "Response body should not be null")

            val actual = Json.decodeFromString<ApiResponse>(responseBody)

            val expected = ApiResponse(success = true, message = "OK", prevPage = 5, nextPage = 2, heroes = heroesRepository.page1)

            println(" The Actual Data is $actual")
            println("The Expected Data is $expected")

            assertEquals(expected= expected, actual= actual)
        }

    }

    @Test
    fun `access all heroes endpoint,query all pages ,assert correct information`() = testApplication {

        application{
            //configureKoin()
            configureRouting()
        }

        val page= 1..5

        val heroes= listOf(heroesRepository.page1,heroesRepository.page2,heroesRepository.page3,heroesRepository.page4,heroesRepository.page5)
        page.forEach { page ->
            client.get("/boruto/heroes?page=$page").apply{

                val calculatedPage = heroesRepository.calculatePage(page)

                println("Before 1st assertEquals the STATUS is :  $status")
                assertEquals(expected = HttpStatusCode.OK, actual = status)

                val expected= ApiResponse(
                    success = true,
                    message = "OK",
                    prevPage =calculatedPage["previousPage"],
                    nextPage = calculatedPage["nextPage"],
                    heroes = heroes[page-1]
                )
                val actual = Json.decodeFromString<ApiResponse>(bodyAsText())

                assertEquals(expected = expected, actual = actual)
            }
        }

    }

    @Test
    fun `access all heroes endpoints, query non existing page number ,assert error`() = testApplication {
        application {
            configureRouting()

        }
        println("TILL HERE OK")//ok
        client.get("/boruto/heroes?page=6").apply{

            println("Response Status1 : ${status}")//ok
            assertEquals(expected = HttpStatusCode.NotFound, actual = status)

            println("Response Status2 : ${status}")

            val expected= ApiResponse(success = false, message = "Heroes not found")

            val responseBody = bodyAsText()

            println("Response Body: $responseBody")

            val actual = Json.decodeFromString<ApiResponse>(responseBody)

            println("THE EXPECTED IS ${expected}")
           println("THE ACTUAL IS ${actual}")
           assertEquals(expected = expected, actual = actual)
        }
    }

    @Test
    fun `access all heroes endpoints, query invalid page number ,assert error`() = testApplication {
        application{
            configureRouting()
        }
        client.get("/boruto/heroes?page=invalid").apply{
            assertEquals(expected = HttpStatusCode.BadRequest, actual = status)
            val expected= ApiResponse(success = false, message = "sorry only numbers are allowed")
            val responseBody = bodyAsText()
            val actual = Json.decodeFromString<ApiResponse>(responseBody)
            assertEquals(expected = expected, actual = actual)
        }
    }

    @Test
    fun `access search hero endpoint, query hero name, assert single hero result`()=testApplication{
       application {
           configureRouting()
       }
        client.get("/boruto/heroes/search?name=sa").apply{
             assertEquals(expected = HttpStatusCode.OK,actual= status)

            val actual= Json.decodeFromString<ApiResponse>(bodyAsText()).heroes.size
            assertEquals(expected= 3 ,actual= actual)

        }

    }

    @Test
    fun `access search hero endpoint, query empty hero list , assert empty list as a result`()= testApplication{
        application {
            configureRouting()
        }
        client.get("/boruto/heroes/search?name=").apply{
            assertEquals(expected = HttpStatusCode.OK,actual= status)
            val actual = Json.decodeFromString<ApiResponse>(bodyAsText()).heroes
            assertEquals(expected= emptyList() ,actual= actual)

        }
    }

    @Test
    fun ` access search hero endpoint, query non exisitng heroes ,assert empty list as a result`()= testApplication{
        application {
            configureRouting()
        }
        client.get("/boruto/heroes/search?name=invalid").apply{
            assertEquals(expected = HttpStatusCode.OK,actual= status)
            val actual = Json.decodeFromString<ApiResponse>(bodyAsText()).heroes
            assertEquals(expected= emptyList() ,actual= actual)
        }
    }






}

