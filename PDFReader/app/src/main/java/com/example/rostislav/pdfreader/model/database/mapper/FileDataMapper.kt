package com.example.rostislav.pdfreader.model.database.mapper

import com.example.rostislav.pdfreader.model.database.DatabaseFileData
import com.example.rostislav.pdfreader.model.database.room.FileData

fun FileData.toDatabaseFileData() = DatabaseFileData(
    url = url,
    localPath = localPath,
    fileName = fileName,
    thumbnail = thumbnail
)

fun DatabaseFileData.toFileData() = FileData(
    url = url,
    localPath = localPath,
    fileName = fileName,
    thumbnail = thumbnail
)