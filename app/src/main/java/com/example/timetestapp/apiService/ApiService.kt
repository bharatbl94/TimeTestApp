package com.example.timetestapp.apiService

import com.example.timetestapp.model.BaseModel
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {
    @GET("api/breeds/image/random")
    suspend fun getRandomDog(): BaseModel?
}
