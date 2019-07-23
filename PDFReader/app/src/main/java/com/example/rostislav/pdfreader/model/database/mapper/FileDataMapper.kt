package com.example.rostislav.pdfreader.model.database.mapper

import com.example.rostislav.pdfreader.entity.FileData
import com.example.rostislav.pdfreader.model.database.room.FileDataRoom

fun FileDataRoom.toFileData() = FileData(
    url = url,
    localPath = localPath,
    fileName = fileName,
    thumbnail = thumbnail
)

fun FileData.toFileDataRoom() = FileDataRoom(
    url = url,
    localPath = localPath,
    fileName = fileName,
    thumbnail = thumbnail
)