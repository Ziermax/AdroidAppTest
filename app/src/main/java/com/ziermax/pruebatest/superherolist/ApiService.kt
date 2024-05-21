package com.ziermax.pruebatest.superherolist

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
	@GET("541e83cd51184187a47375382f12da58/search/{name}")
	suspend fun getSuperHeroes(@Path("name") superHeroName: String): Response<SuperHeroDataResponse>

	@GET("541e83cd51184187a47375382f12da58/{heroId}")
	suspend fun getHeroDetailedInfo(@Path("heroId") superHeroName: String): Response<HeroDetailedResponse>
}