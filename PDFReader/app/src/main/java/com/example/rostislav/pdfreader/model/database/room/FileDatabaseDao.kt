package com.example.rostislav.pdfreader.model.database.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface FileDatabaseDao {
    @Insert
    fun insert(fileData: FileData)

    @Insert
    fun insertAllData(listOfFileData: List<FileData>)

    @Update
    fun update(fileData: FileData)

    @Query("SELECT * FROM file_database WHERE url = :url")
    fun query(url: String): FileData

    @Query("SELECT * FROM file_database ")
    fun queryAllData(): List<FileData>
}