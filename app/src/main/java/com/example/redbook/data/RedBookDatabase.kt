package com.example.redbook.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.redbook.data.dao.AnimalDao
import com.example.redbook.data.model.Animal

@Database(entities = [Animal::class],version = 2)
abstract class RedBookDatabase:RoomDatabase() {

    companion object {
        private lateinit var INSTANCE: RedBookDatabase

        fun getInstance(context: Context): RedBookDatabase =
            if (::INSTANCE.isInitialized) {
                INSTANCE
            } else {
                INSTANCE=Room.databaseBuilder(
                    context,
                    RedBookDatabase::class.java, "book-database.db"
                )
                    .allowMainThreadQueries()
                    .createFromAsset("book-database.db")
                    .build()
                INSTANCE
            }
    }
    abstract fun dao():AnimalDao
}