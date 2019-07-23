package com.example.rostislav.pdfreader.model.database

import com.example.rostislav.pdfreader.entity.FileData

interface Database {
    fun insert()

    fun update(fileData: FileData)

    fun getData(key: String): FileData

    fun getAllData(): List<FileData>
}