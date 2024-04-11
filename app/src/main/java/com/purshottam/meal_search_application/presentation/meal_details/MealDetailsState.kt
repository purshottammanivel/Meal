package com.purshottam.meal_search_application.presentation.meal_details

import com.purshottam.meal_search_application.domain.model.MealDetails

data class MealDetailsState(
    val data: MealDetails? = null,
    val error: String = "",
    val isLoading: Boolean = false
)
