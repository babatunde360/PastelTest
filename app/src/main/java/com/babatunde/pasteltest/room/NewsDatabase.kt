package com.babatunde.pasteltest.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.babatunde.pasteltest.model.Article

@Database(entities = [Article::class], exportSchema = false, version = 1)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun dbNewsDao(): NewsDao

    // companion object that allow the database name to be called anywhere in the codebase
    companion object{
        const val db_Name = "newsDB"

    }
}