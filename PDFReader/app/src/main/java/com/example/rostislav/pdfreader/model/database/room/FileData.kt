package com.example.rostislav.pdfreader.model.database.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rostislav.pdfreader.utils.NamesOfFiles

@Entity(tableName = "file_database")
data class FileData(
    @PrimaryKey
    val url: String = NamesOfFiles.EMPTY_STRING,
    @ColumnInfo(name = "local_path")
    val localPath: String = NamesOfFiles.EMPTY_STRING,
    @ColumnInfo(name = "file_name")
    val fileName: String = NamesOfFiles.EMPTY_STRING,
    @ColumnInfo(name = "file_thumbnail")
    val thumbnail: String = NamesOfFiles.EMPTY_STRING
)