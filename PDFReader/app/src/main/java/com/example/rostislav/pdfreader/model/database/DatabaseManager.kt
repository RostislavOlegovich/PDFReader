package com.example.rostislav.pdfreader.model.database

import android.content.Context
import com.example.rostislav.pdfreader.model.database.room.FileData
import com.example.rostislav.pdfreader.utils.DatabaseInitializer

class DatabaseManager(context: Context) : Database {

    private val appDatabase = DatabaseInitializer.getInstance(context)

    override fun insert() {
    }

    override fun update() {
    }

    override fun get(key: String): FileData {
        return appDatabase.fileDatabaseDao.get(key)
    }

    override fun getAll(): MutableList<FileData> {
        return appDatabase.fileDatabaseDao.getAll()
    }
}