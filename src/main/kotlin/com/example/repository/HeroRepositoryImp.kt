package com.example.repository

import com.example.models.ApiResponse
import com.example.models.Hero

class HeroRepositoryImp() : HeroRepository {


    override val heross: Map<Int, List<Hero>>
        get()= TODO()
    override val page1: List<Hero>
        get()=TODO()
    override val page2: List<Hero>
        get()=TODO()
    override val page3: List<Hero>
        get()=TODO()
    override val page4: List<Hero>
        get()=TODO()
    override val page5: List<Hero>
        get()=TODO()



    override suspend fun getAllHeroes(page: Int): ApiResponse {
        TODO("Not yet implemented")
    }

    override fun searchHeroes(name: String): ApiResponse {
        TODO("Not yet implemented")
    }


}