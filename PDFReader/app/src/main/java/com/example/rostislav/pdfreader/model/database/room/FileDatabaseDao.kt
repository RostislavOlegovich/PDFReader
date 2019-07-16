package com.example.rostislav.pdfreader.model.database.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface FileDatabaseDao {
    @Insert
    fun insert(fileData: FileData)

    @Update
    fun update(fileData: FileData)

    @Query("SELECT * from file_database WHERE url = :key")
    fun get(key: String): FileData

    @Query("SELECT * from file_database")
    fun getAll(): MutableList<FileData>

    @Insert
    fun insertAll(listOfFileData: List<FileData>)
}