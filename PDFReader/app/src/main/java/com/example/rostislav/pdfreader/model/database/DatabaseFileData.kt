package com.example.rostislav.pdfreader.model.database

import com.example.rostislav.pdfreader.utils.NamesOfFiles

data class DatabaseFileData(
    var url: String = NamesOfFiles.EMPTY_STRING,
    val localPath: String = NamesOfFiles.EMPTY_STRING,
    var fileName: String = NamesOfFiles.EMPTY_STRING
)