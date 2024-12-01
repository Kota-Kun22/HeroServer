package com.example.repository

import com.example.models.ApiResponse
import com.example.models.Hero

class HeroRepositoryImp(
    override val heross: Map<Int, List<Hero>>,
    override val page1: List<Hero>,
    override val page2: List<Hero>,
    override val page3: List<Hero>,
    override val page4: List<Hero>,
    override val page5: List<Hero>
) : HeroRepository {

    override suspend fun getAllHeroes(page: Int): ApiResponse {
        TODO("Not yet implemented")
    }

    override fun searchHeroes(name: String): ApiResponse {
        TODO("Not yet implemented")
    }
}