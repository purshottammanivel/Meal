package com.purshottam.meal_search_application.di

import com.purshottam.meal_search_application.data.remote.IMealSearchApi
import com.purshottam.meal_search_application.data.repository.MealDetailsImpl
import com.purshottam.meal_search_application.data.repository.MealSearchImpl
import com.purshottam.meal_search_application.domain.repository.IMealDetailsRepository
import com.purshottam.meal_search_application.domain.repository.IMealSearchRepository
import com.purshottam.meal_search_application.utility.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    @Provides
    @Singleton
    fun provideMealSearchApi(): IMealSearchApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IMealSearchApi::class.java)
    }

    @Provides
    fun provideIMealSearchRepository(mealSearchApi: IMealSearchApi): IMealSearchRepository {
        return MealSearchImpl(mealSearchApi)
    }

    @Provides
    fun provideIMealDetailsRepository(mealSearchApi: IMealSearchApi): IMealDetailsRepository {
        return MealDetailsImpl(mealSearchApi)
    }
}