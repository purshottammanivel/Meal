package com.purshottam.meal_search_application.domain.repository

import com.purshottam.meal_search_application.data.model.MealsDto

interface IMealDetailsRepository {

    suspend fun getMealDetails(id: String): MealsDto
}