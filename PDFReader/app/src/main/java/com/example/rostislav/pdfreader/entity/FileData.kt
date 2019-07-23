package com.example.rostislav.pdfreader.entity

import com.example.rostislav.pdfreader.utils.emptyString

data class FileData(
    val url: String = emptyString(),
    val localPath: String = emptyString(),
    val fileName: String = emptyString(),
    val thumbnail: String = emptyString()
)