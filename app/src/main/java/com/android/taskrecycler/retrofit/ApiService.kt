package com.android.taskrecycler.retrofit

import retrofit2.http.GET

interface ApiService {
    @GET("todos/1")
    suspend fun getTodo(): TodoResponse
}



