package com.example.rostislav.pdfreader.model.database

import com.example.rostislav.pdfreader.utils.NamesOfFiles

data class DatabaseFileData(
    val url: String = NamesOfFiles.EMPTY_STRING,
    val localPath: String = NamesOfFiles.EMPTY_STRING,
    val fileName: String = NamesOfFiles.EMPTY_STRING,
    val thumbnail: String = NamesOfFiles.EMPTY_STRING
)