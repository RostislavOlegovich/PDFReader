package com.example.rostislav.pdfreader.model.database

import android.content.Context
import com.example.rostislav.pdfreader.entity.FileData
import com.example.rostislav.pdfreader.model.database.mapper.toFileData
import com.example.rostislav.pdfreader.model.database.mapper.toFileDataRoom
import com.example.rostislav.pdfreader.model.database.room.utils.DatabaseInitializer

class DatabaseManager(context: Context) : Database {

    private val appDatabase = DatabaseInitializer.getInstance(context)

    override fun insert() {
    }

    override fun update(fileData: FileData) {
        appDatabase.fileDatabaseDao.update(fileData.toFileDataRoom())
    }

    override fun getData(key: String): FileData {
        return appDatabase.fileDatabaseDao.query(key).toFileData()
    }

    override fun getAllData(): List<FileData> {
        return appDatabase.fileDatabaseDao.queryAllData().map { it.toFileData() }
    }
}