package com.android.taskrecycler.retrofit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ApiViewModel(private val repository: ApiRepository) : ViewModel() {
    val data = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()

    fun fetchData() {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val response = repository.fetchTodo()
                // Show title from the response as an example
                data.value = "ID: ${response.id}\nTitle: ${response.title} \n UserID:${response.userId}"
                println("api successcall")
            } catch (e: Exception) {
                println("api fail ${e.message}")
                data.value = "Error: ${e.message}"
            } finally {
                isLoading.value = false
            }
        }
    }
}