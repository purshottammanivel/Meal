package com.purshottam.meal_search_application.domain.use_case

import com.purshottam.meal_search_application.data.model.toDomainMeal
import com.purshottam.meal_search_application.domain.model.Meal
import com.purshottam.meal_search_application.domain.repository.IMealSearchRepository
import com.purshottam.meal_search_application.utility.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MealSearchListUseCase @Inject constructor(
    private val mealSearchRepository: IMealSearchRepository
) {

    operator fun invoke(s: String): Flow<Resource<List<Meal>>> {
        return flow {
            try {

                emit(Resource.Loading())

                val response = mealSearchRepository.getMealList(s)
                val list =
                    if (response.meals.isNullOrEmpty()) emptyList<Meal>()
                    else response.meals.map { it.toDomainMeal() }

                emit(Resource.Success(data = list))

            } catch (e: HttpException) {

                emit(Resource.Error(message = e.localizedMessage ?: "HttpsException"))

            } catch (e: IOException) {

                emit(Resource.Error(message = e.localizedMessage ?: "IOException"))

            } catch (e: Exception) {

                emit(Resource.Error(message = e.localizedMessage ?: "Exception"))

            }
        }
    }
}