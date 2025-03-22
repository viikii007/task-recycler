package com.android.taskrecycler.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT
            )
        """.trimIndent()
        db.execSQL(createTableQuery)
        insertInitialData(db)
    }

    private fun insertInitialData(db: SQLiteDatabase) {
        // Only insert data if table is empty
        val cursor = db.rawQuery("SELECT COUNT(*) FROM $TABLE_NAME", null)
        cursor.moveToFirst()
        val count = cursor.getInt(0)
        cursor.close()

        if (count == 0) {
            db.beginTransaction()
            try {
                for (i in 1..1000) {
                    val values = ContentValues().apply {
                        put(COLUMN_NAME, "Item $i")
                    }
                    db.insert(TABLE_NAME, null, values)
                }
                db.setTransactionSuccessful()
            } finally {
                db.endTransaction()
            }
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    companion object {
        private const val DATABASE_NAME = "items.db"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "items"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
    }
}