package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val sucess : Boolean,
    val message : String?=null,

    val prevPage :Int?=null,
    val nextPage :Int?=null,

    val heros :List<Hero> = emptyList(),

)
