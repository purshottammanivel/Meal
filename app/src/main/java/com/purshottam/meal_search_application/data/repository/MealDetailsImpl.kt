package com.purshottam.meal_search_application.data.repository

import com.purshottam.meal_search_application.data.model.MealsDto
import com.purshottam.meal_search_application.data.remote.IMealSearchApi
import com.purshottam.meal_search_application.domain.repository.IMealDetailsRepository

class MealDetailsImpl(
    private val mealSearchApi: IMealSearchApi
) : IMealDetailsRepository {

    override suspend fun getMealDetails(id: String): MealsDto {
        return mealSearchApi.getMealDetails(id)
    }
}