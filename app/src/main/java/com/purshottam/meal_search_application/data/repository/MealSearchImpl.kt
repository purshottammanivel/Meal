package com.purshottam.meal_search_application.data.repository

import com.purshottam.meal_search_application.data.model.MealsDto
import com.purshottam.meal_search_application.data.remote.IMealSearchApi
import com.purshottam.meal_search_application.domain.repository.IMealSearchRepository

class MealSearchImpl(
    private val mealSearchApi: IMealSearchApi
) : IMealSearchRepository {

    override suspend fun getMealList(s: String): MealsDto {
        return mealSearchApi.getMealList(s)
    }
}