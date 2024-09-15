package com.example.mi_primera_api

import com.example.mi_primera_api.Data.SuperHeroDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperHeroesService {

    @GET("/api/889ea4e7d7b48d5586b1ecd07925d399/search/{name}")
    suspend fun getSuperHeroesByName(@Path("name")nombre:String):Response<SuperHeroDataResponse>

}