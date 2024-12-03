package com.example.routes

import com.example.models.ApiResponse
import com.example.repository.HeroRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import org.koin.ktor.ext.inject

fun Route.getAllHeroes(){

    val heroRepositoroy : HeroRepository by inject()

    get("/boruto/heroes"){
       try {
           val page= call.request.queryParameters["page"]?.toInt() ?: 1
           require(page in 1..5)

           val apiResponse = heroRepositoroy.getAllHeroes(page = page)// this page i will get from the client side okay

           call.respond(message = apiResponse, status = HttpStatusCode.OK)

       }catch (e:NumberFormatException){
           call.respond(
               message = ApiResponse( success = false, message = "sorry only numbers are allowed",),
               status= HttpStatusCode.BadRequest
           )
       }catch (e:IllegalArgumentException){
           call.respond(
               message= ApiResponse(success= false,message = " Heroes not found and only 1-5 numbers are allowed"),
               status = HttpStatusCode.NotFound
           )
       }
    }
}


