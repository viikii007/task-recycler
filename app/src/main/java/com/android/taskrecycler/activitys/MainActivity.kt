package com.android.taskrecycler.activitys

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.taskrecycler.ItemAdapter
import com.android.taskrecycler.R
import com.android.taskrecycler.database.ItemRepository
import com.android.taskrecycler.database.ItemViewModel
import com.android.taskrecycler.retrofit.ApiRepository
import com.android.taskrecycler.retrofit.ApiViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ItemViewModel
    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        adapter = ItemAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

         adapter.itemClickListener = { item ->
            val intent = Intent(this, ItemDetailActivity::class.java)
            intent.putExtra(ItemDetailActivity.EXTRA_ITEM_NAME, item)
            startActivity(intent)
        }

        val repository = ItemRepository(this)
        viewModel = ViewModelProvider(this, ViewModelFactory(repository))
            .get(ItemViewModel::class.java)

        viewModel.newItems.observe(this) { newBatch ->
            adapter.addItems(newBatch)
        }

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(rv: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(rv, dx, dy)
                val layoutManager = rv.layoutManager as LinearLayoutManager
                if (layoutManager.findLastVisibleItemPosition() == adapter.itemCount - 1) {
                    viewModel.loadMoreItems()
                }
            }
        })

         viewModel.loadMoreItems()
    }
}


class ViewModelFactory(private val repository: ItemRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ItemViewModel(repository) as T
        }
        throw IllegalArgumentException("errro")
    }
}

class ApiViewModelFactory(private val repository: ApiRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ApiViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ApiViewModel(repository) as T
        }
        throw IllegalArgumentException("error")
    }
}