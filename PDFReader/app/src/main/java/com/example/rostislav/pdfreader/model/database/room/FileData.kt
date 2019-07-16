package com.example.rostislav.pdfreader.model.database.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rostislav.pdfreader.utils.NamesOfFiles

@Entity(tableName = NamesOfFiles.DATABASE_NAME)
data class FileData(
    @PrimaryKey
    var url: String = NamesOfFiles.EMPTY_STRING,

    @ColumnInfo(name = NamesOfFiles.COLUMN_LOCAL_PATH_NAME)
    val localPath: String = NamesOfFiles.EMPTY_STRING,

    @ColumnInfo(name = NamesOfFiles.COLUMN_FILE_NAME)
    var fileName: String = NamesOfFiles.EMPTY_STRING
)