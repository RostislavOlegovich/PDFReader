package com.example.rostislav.pdfreader.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rostislav.pdfreader.model.database.entity.FileData
import com.example.rostislav.pdfreader.utils.FileConverter

@Database(entities = [FileData::class], version = 1, exportSchema = false)
@TypeConverters(FileConverter::class)
abstract class FileDatabase : RoomDatabase() {
    abstract val fileDao: FileDao

    companion object {
        @Volatile
        private var INSTANCE: FileDatabase? = null

        fun getInstance(context: Context): FileDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FileDatabase::class.java,
                        "file_database"
                    )
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}