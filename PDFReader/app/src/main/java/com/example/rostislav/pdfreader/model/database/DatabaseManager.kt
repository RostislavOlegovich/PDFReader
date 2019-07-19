package com.example.rostislav.pdfreader.model.database

import android.content.Context
import com.example.rostislav.pdfreader.model.database.mapper.toDatabaseFileData
import com.example.rostislav.pdfreader.model.database.mapper.toFileData
import com.example.rostislav.pdfreader.utils.DatabaseInitializer

class DatabaseManager(context: Context) : Database {

    private val appDatabase = DatabaseInitializer.getInstance(context)

    override fun insert() {
    }

    override fun update(fileData: DatabaseFileData) {
        appDatabase.fileDatabaseDao.update(fileData.toFileData())
    }

    override fun getData(key: String): DatabaseFileData {
        return appDatabase.fileDatabaseDao.query(key).toDatabaseFileData()
    }

    override fun getAllData(): List<DatabaseFileData> {
        return appDatabase.fileDatabaseDao.queryAllData().map { it.toDatabaseFileData() }
    }
}