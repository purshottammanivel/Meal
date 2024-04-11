package com.purshottam.meal_search_application.domain.use_case

import com.purshottam.meal_search_application.data.model.toDomainMealDetails
import com.purshottam.meal_search_application.domain.model.MealDetails
import com.purshottam.meal_search_application.domain.repository.IMealDetailsRepository
import com.purshottam.meal_search_application.utility.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MealDetailsUseCase @Inject constructor(
    private val mealDetailsRepository: IMealDetailsRepository
) {

    operator fun invoke(id: String): Flow<Resource<MealDetails>> {
        return flow {
            try {
                emit(Resource.Loading())

                val response =
                    mealDetailsRepository.getMealDetails(id).meals[0].toDomainMealDetails()

                emit(Resource.Success(data = response))

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