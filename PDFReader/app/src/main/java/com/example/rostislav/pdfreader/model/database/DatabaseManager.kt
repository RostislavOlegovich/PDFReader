package com.example.rostislav.pdfreader.model.database

import android.content.Context
import com.example.rostislav.pdfreader.model.database.room.FileData
import com.example.rostislav.pdfreader.utils.DatabaseInitializer

class DatabaseManager(context: Context) : Database {

    private val appDatabase = DatabaseInitializer.getInstance(context)

    override fun insert() {
    }

    override fun update(fileData: FileData) {
        appDatabase.fileDatabaseDao.update(fileData)
    }

    override fun getData(key: String): FileData {
        return appDatabase.fileDatabaseDao.query(key)
    }

    override fun getAllData(): List<FileData> {
        return appDatabase.fileDatabaseDao.queryAllData()
    }
}