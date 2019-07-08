package com.example.rostislav.pdfreader.model.database

import android.content.Context
import com.example.rostislav.pdfreader.model.database.entity.FileData
import com.example.rostislav.pdfreader.utils.FileConverter
import java.io.File

class DatabaseManager(context: Context) : Database {
    private val fileDatabase = FileDatabase.getInstance(context)

    override fun setData(context: Context) {
        val directory = context.cacheDir
        val file = File(directory, "file")
        fileDatabase.fileDao.insert(FileData(0, "file", FileConverter.fromFile(file)))
    }

    override fun getData(): File {
        val file = fileDatabase.fileDao.getFile("file").filename
        return FileConverter.toFile(file)
    }

}