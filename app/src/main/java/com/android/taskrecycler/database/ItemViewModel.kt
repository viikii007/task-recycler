package com.android.taskrecycler.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class ItemViewModel(private val repository: ItemRepository) : ViewModel() {

    private val _newItems = MutableLiveData<List<String>>()
    val newItems: LiveData<List<String>> get() = _newItems

    private var currentPage = 0
    private val pageSize = 20
    private var isLastPage = false

    fun loadMoreItems() {
        if (isLastPage) return

        val newItemsList = repository.getItems(pageSize, currentPage * pageSize)
        if (newItemsList.isEmpty()) {
            isLastPage = true
            return
        }
        _newItems.value = newItemsList
        currentPage++
    }
}

