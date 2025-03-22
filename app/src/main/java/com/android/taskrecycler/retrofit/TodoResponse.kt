package com.android.taskrecycler.retrofit

data class TodoResponse(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)