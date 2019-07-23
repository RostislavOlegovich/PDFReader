package com.example.rostislav.pdfreader.model.database.room.utils

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.rostislav.pdfreader.model.database.room.AppDatabase
import org.jetbrains.anko.doAsync

object DatabaseInitializer {
    private var appDatabase: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase {
        synchronized(this) {
            var instance = appDatabase

            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "file_database"
                )
                    .addCallback(callback)
                    .build()
                appDatabase = instance
            }
            return instance
        }
    }

    private val callback = object : RoomDatabase.Callback() {
        override fun onCreate(database: SupportSQLiteDatabase) {
            super.onCreate(database)
            doAsync {
                appDatabase!!.fileDatabaseDao.insertAllData(
                    DatabaseCreator.fileList
                )
            }
        }
    }
}