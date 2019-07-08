package com.example.rostislav.pdfreader.model.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "file_database")
data class FileData(
    @PrimaryKey(autoGenerate = true)
    var fileId: Long = 0L,

    @ColumnInfo(name = "file_name")
    val filename: String? = null,

    @ColumnInfo(name = "file_data")
    val fileData: String? = null
)