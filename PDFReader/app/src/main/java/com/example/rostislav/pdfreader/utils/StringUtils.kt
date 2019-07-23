package com.example.rostislav.pdfreader.utils

fun getNameFromString(string: String) = string.substringAfterLast("/", string)

fun changeName(string: String) = string.replaceAfter(".", "png", string)

fun emptyString() = ""