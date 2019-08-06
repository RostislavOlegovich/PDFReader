package com.example.rostislav.pdfreader.model.database.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rostislav.pdfreader.utils.StringUtils.emptyString

@Entity(tableName = "file_database")
data class FileDataRoom(
    @PrimaryKey
    val url: String = emptyString(),
    @ColumnInfo(name = "local_path")
    val localPath: String = emptyString(),
    @ColumnInfo(name = "file_name")
    val fileName: String = emptyString(),
    @ColumnInfo(name = "file_thumbnail")
    val thumbnail: String = emptyString()
)