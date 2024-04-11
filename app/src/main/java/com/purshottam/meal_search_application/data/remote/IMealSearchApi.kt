package com.purshottam.meal_search_application.data.remote

import com.purshottam.meal_search_application.data.model.MealsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface IMealSearchApi {

    @GET("api/json/v1/1/search.php")
    suspend fun getMealList(@Query("s") s: String): MealsDto

    @GET("api/json/v1/1/lookup.php")
    suspend fun getMealDetails(@Query("i") i: String): MealsDto
}