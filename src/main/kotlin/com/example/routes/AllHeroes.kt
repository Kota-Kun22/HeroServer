package com.example.routes

import com.example.models.ApiResponse
import com.example.repository.HeroRepository
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.contentType
import io.ktor.server.routing.get
import kotlinx.serialization.json.Json
import org.koin.ktor.ext.inject

/**
 *
 */
fun Route.getAllHeroes(){

    val heroRepositoroy : HeroRepository by inject()

    get("/boruto/heroes"){
       try {
           val page= call.request.queryParameters["page"]?.toInt() ?: 1
           require(page in 1..5)

           val apiResponse = heroRepositoroy.getAllHeroes(page = page)// this page I will get from the client side okay

          //call.respond(message = apiResponse, status = HttpStatusCode.OK)
           val jsonResponse = Json.encodeToString(ApiResponse.serializer(), apiResponse)

           // Use respondText to send the JSON response with content type
           call.respondText(text = jsonResponse, contentType = ContentType.Application.Json, status = HttpStatusCode.OK)

       }catch (e:NumberFormatException){
           val errorResponse = ApiResponse(success = false, message = "sorry only numbers are allowed")
           val jsonResponse = Json.encodeToString(ApiResponse.serializer(), errorResponse)
           call.respondText(text = jsonResponse, contentType = ContentType.Application.Json, status = HttpStatusCode.BadRequest)

       }catch (e:IllegalArgumentException){
           // Handle out-of-range page numbers
           val errorResponse = ApiResponse(
               success = false,
               message = "Heroes not found"
           )
           val jsonResponse = Json.encodeToString(ApiResponse.serializer(), errorResponse)

           call.respondText(
               text = jsonResponse,
               contentType = ContentType.Application.Json,
               status = HttpStatusCode.NotFound)

       }
    }
}


