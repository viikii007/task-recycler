package com.android.taskrecycler.activitys

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.android.taskrecycler.R
import com.android.taskrecycler.ApiFragment

class ItemDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_detail)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val itemName = intent.getStringExtra(EXTRA_ITEM_NAME) ?: "No Item fpund"

        supportActionBar?.title = itemName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

         val detailTextView: TextView = findViewById(R.id.detailTextView)
        detailTextView.text = itemName

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, ApiFragment())
            .commit()
    }

     override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val EXTRA_ITEM_NAME = "extra_item_name"
    }
}