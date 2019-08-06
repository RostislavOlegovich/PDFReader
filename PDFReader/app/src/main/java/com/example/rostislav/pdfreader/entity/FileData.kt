package com.example.rostislav.pdfreader.entity

import com.example.rostislav.pdfreader.utils.StringUtils

data class FileData(
    val url: String = StringUtils.emptyString(),
    val localPath: String = StringUtils.emptyString(),
    val fileName: String = StringUtils.emptyString(),
    val thumbnail: String = StringUtils.emptyString()
)