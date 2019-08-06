package com.example.rostislav.pdfreader.model.database

import com.example.rostislav.pdfreader.core.interfaces.Manager
import com.example.rostislav.pdfreader.entity.FileData

interface Database : Manager {
    fun insert()

    fun delete(fileData: FileData)

    fun update(fileData: FileData)

    fun getData(key: String): FileData

    fun getAllData(): List<FileData>
}