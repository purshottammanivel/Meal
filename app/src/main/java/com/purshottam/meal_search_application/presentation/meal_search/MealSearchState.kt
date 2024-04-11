package com.purshottam.meal_search_application.presentation.meal_search

import com.purshottam.meal_search_application.domain.model.Meal

data class MealSearchState(
    val data: List<Meal>? = null,
    val error: String = "",
    val isLoading: Boolean = false
)