package com.example.timetestapp.apiService

import com.example.timetestapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private const val readTimeOut: Long = 50
    private const val writeTimeOut: Long = 60
    private const val connectionTimeOut: Long = 50
    private const val Base_URL = "https://dog.ceo/"

    fun getClient(): ApiService {
        val okHttpClient = OkHttpClient.Builder().run {
            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                addInterceptor(logging)
            }
            connectTimeout(connectionTimeOut, TimeUnit.SECONDS)
            readTimeout(readTimeOut, TimeUnit.SECONDS)
            writeTimeout(writeTimeOut, TimeUnit.SECONDS)
            build()
        }
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Base_URL)
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }
}

