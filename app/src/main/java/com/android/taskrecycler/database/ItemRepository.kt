package com.android.taskrecycler.database

import android.content.Context


class ItemRepository(context: Context) {
    private val dbHelper = MyDatabaseHelper(context)

    fun getItems(limit: Int, offset: Int): List<String> {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery(
            "SELECT ${MyDatabaseHelper.COLUMN_NAME} FROM ${MyDatabaseHelper.TABLE_NAME} ORDER BY ${MyDatabaseHelper.COLUMN_ID} ASC LIMIT ? OFFSET ?",
            arrayOf(limit.toString(), offset.toString())
        )
        val items = mutableListOf<String>()
        while (cursor.moveToNext()) {
            items.add(cursor.getString(0))
        }
        cursor.close()
        return items
    }
}
