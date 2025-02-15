package com.example.timetestapp

import com.example.timetestapp.apiService.ApiClient
import com.example.timetestapp.model.BaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class GenerateDogRepository {
    private val apiService = ApiClient.getClient()

    fun getRandomDog(): Flow<BaseModel> = flow {
        apiService.getRandomDog()?.let { emit(it) } // Fetch data and emit as Flow
    }
}