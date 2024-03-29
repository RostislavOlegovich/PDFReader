package com.example.rostislav.pdfreader.model.database.room

import androidx.room.*

@Dao
interface FileDatabaseDao {
    @Insert
    fun insert(fileDataRoom: FileDataRoom)

    @Delete
    fun delete(fileDataRoom: FileDataRoom)

    @Insert
    fun insertAllData(listOfFileDataRoom: List<FileDataRoom>)

    @Update
    fun update(fileDataRoom: FileDataRoom)

    @Query("SELECT * FROM file_database WHERE url = :url")
    fun query(url: String): FileDataRoom

    @Query("SELECT * FROM file_database ")
    fun queryAllData(): List<FileDataRoom>
}