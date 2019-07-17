package com.example.rostislav.pdfreader.utils.extention

import com.example.rostislav.pdfreader.model.database.DatabaseFileData
import com.example.rostislav.pdfreader.model.database.room.FileData

fun FileData.toDatabaseFileData() = DatabaseFileData(
    url = url,
    localPath = localPath,
    fileName = fileName
)