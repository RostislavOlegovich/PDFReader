package com.example.rostislav.pdfreader.model.database

interface Database {
    fun insert()

    fun update(fileData: DatabaseFileData)

    fun getData(key: String): DatabaseFileData

    fun getAllData(): List<DatabaseFileData>
}