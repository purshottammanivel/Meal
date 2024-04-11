package com.purshottam.meal_search_application.presentation.meal_details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.purshottam.meal_search_application.domain.use_case.MealDetailsUseCase
import com.purshottam.meal_search_application.utility.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MealDetailsViewModel @Inject constructor(
    private val mealDetailsUseCase: MealDetailsUseCase
) : ViewModel() {

    private val _mealDetailsMutableStateFlow =
        MutableStateFlow<MealDetailsState>(MealDetailsState())
    val mealDetailsStateFlow: StateFlow<MealDetailsState> = _mealDetailsMutableStateFlow

    fun getMealDetails(id: String) {

        mealDetailsUseCase(id).onEach {
            when (it) {
                is Resource.Loading -> {
                    _mealDetailsMutableStateFlow.value = MealDetailsState(isLoading = true)
                }

                is Resource.Error -> {
                    _mealDetailsMutableStateFlow.value = MealDetailsState(error = it.message ?: "")
                }

                is Resource.Success -> {
                    Log.d("QWERTY", "getMealDetails: dataReceivedFromAPIOnVM --> ${it.data}")
                    _mealDetailsMutableStateFlow.value = MealDetailsState(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}