package com.example.rostislav.pdfreader.utils

object StringUtils {
    fun getNameFromString(string: String) = string.substringAfterLast("/", string)

    fun changeName(string: String) = string.replaceAfter(".", "png", string)

    fun emptyString() = ""

    fun getExtraStringIntent() = "EXTRA_STRING"
}