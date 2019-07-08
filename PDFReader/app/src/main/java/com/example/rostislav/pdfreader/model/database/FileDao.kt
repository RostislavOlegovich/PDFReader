package com.example.rostislav.pdfreader.model.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.rostislav.pdfreader.model.database.entity.FileData

@Dao
interface FileDao {
    @Insert
    fun insert(fileData: FileData)

    @Delete
    fun delete(fileData: FileData)

    @Query("SELECT file_name FROM file_database WHERE file_name = :filename")
    fun getFile(filename: String): FileData
}