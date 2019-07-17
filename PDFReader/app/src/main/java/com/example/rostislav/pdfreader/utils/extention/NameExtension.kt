package com.example.rostislav.pdfreader.utils.extention

fun String.getNameFromString(): String {
    return substringAfterLast("/", this)
}