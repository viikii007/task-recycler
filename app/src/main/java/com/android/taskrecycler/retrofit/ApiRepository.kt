package com.android.taskrecycler.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiRepository {
    private val apiService: ApiService
    private val baseUrl="https://jsonplaceholder.typicode.com/"

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)
    }

    suspend fun fetchTodo(): TodoResponse {
        return apiService.getTodo()
    }
}