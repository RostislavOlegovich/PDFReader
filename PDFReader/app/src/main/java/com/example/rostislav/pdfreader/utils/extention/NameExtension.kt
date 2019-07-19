package com.example.rostislav.pdfreader.utils.extention

fun String.getNameFromString(): String {
    return substringAfterLast("/", this)
}

fun String.changeName(): String {
    return replaceAfter(".", "png", this)
}