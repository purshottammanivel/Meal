package com.purshottam.meal_search_application.presentation.meal_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.purshottam.meal_search_application.domain.use_case.MealSearchListUseCase
import com.purshottam.meal_search_application.utility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MealSearchViewModel @Inject constructor(
    private val mealSearchListUseCase: MealSearchListUseCase
) : ViewModel() {

    private val _mealSearchMutableStateFlow = MutableStateFlow<MealSearchState>(MealSearchState())
    val mealSearchStateFlow: StateFlow<MealSearchState> = _mealSearchMutableStateFlow

    fun searchMealList(s: String) {
        mealSearchListUseCase(s).onEach {
            when (it) {

                is Resource.Loading -> {
                    _mealSearchMutableStateFlow.value = MealSearchState(isLoading = true)
                }

                is Resource.Error -> {
                    _mealSearchMutableStateFlow.value =
                        MealSearchState(error = it.message ?: "Error")
                }

                is Resource.Success -> {
                    _mealSearchMutableStateFlow.value = MealSearchState(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}