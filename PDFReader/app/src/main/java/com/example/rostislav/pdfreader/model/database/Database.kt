package com.example.rostislav.pdfreader.model.database

import com.example.rostislav.pdfreader.model.database.room.FileData

interface Database {
    fun insert()

    fun update(fileData: FileData)

    fun get(key: String): FileData

    fun getAll(): MutableList<FileData>
}