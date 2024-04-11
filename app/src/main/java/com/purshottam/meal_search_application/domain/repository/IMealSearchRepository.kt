package com.purshottam.meal_search_application.domain.repository

import com.purshottam.meal_search_application.data.model.MealsDto

interface IMealSearchRepository {

    suspend fun getMealList(s: String): MealsDto
}