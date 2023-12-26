package com.example.hanzicounter.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Text::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun TextDao(): TextDao
}
