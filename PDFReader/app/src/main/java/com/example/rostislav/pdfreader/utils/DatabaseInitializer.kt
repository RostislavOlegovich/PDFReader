package com.example.rostislav.pdfreader.utils

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.rostislav.pdfreader.model.database.room.AppDatabase
import java.util.concurrent.Executors

object DatabaseInitializer {
    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase {
        synchronized(this) {
            var instance = INSTANCE

            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    NamesOfFiles.DATABASE_NAME
                )
                    .addCallback(callback)
                    .build()
                INSTANCE = instance
            }
            return instance
        }
    }

    private val callback = object : RoomDatabase.Callback() {
        override fun onCreate(database: SupportSQLiteDatabase) {
            super.onCreate(database)
            val fileList = DatabaseCreator.fileList
            Executors.newSingleThreadExecutor().execute {
                INSTANCE?.fileDatabaseDao?.insertAll(fileList)
            }
        }
    }
}