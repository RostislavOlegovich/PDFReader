package com.example.rostislav.pdfreader.model.database.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FileDataRoom::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val fileDatabaseDao: FileDatabaseDao
}