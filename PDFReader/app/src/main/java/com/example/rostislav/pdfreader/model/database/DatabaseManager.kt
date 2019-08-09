package com.example.rostislav.pdfreader.model.database

import android.content.Context
import com.example.rostislav.pdfreader.core.base.BaseObservable
import com.example.rostislav.pdfreader.core.observer.Observable
import com.example.rostislav.pdfreader.core.observer.Observer
import com.example.rostislav.pdfreader.entity.FileData
import com.example.rostislav.pdfreader.model.database.mapper.toFileData
import com.example.rostislav.pdfreader.model.database.mapper.toFileDataRoom
import com.example.rostislav.pdfreader.model.database.room.utils.DatabaseInitializer

class DatabaseManager(context: Context) : Database, Observable<FileData, Observer<FileData>> by BaseObservable() {
    private val appDatabase = DatabaseInitializer.getInstance(context)

    override fun insert() {}

    override fun delete(fileData: FileData) {
        appDatabase.fileDatabaseDao.delete(fileData.toFileDataRoom())
    }

    override fun update(fileData: FileData) {
        appDatabase.fileDatabaseDao.update(fileData.toFileDataRoom())
        notifyObserversChange(fileData)
    }

    override fun getObservable() = this as Observable<FileData, Observer<FileData>>

    override fun getData(key: String) = appDatabase.fileDatabaseDao.query(key).toFileData()

    override fun getAllData() = appDatabase.fileDatabaseDao.queryAllData().map { it.toFileData() }

}