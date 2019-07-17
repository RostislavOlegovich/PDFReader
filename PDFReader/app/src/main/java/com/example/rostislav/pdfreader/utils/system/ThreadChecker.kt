package com.example.rostislav.pdfreader.utils.system

object ThreadChecker {
    fun getNumberOfCores(): Int {
        return Runtime.getRuntime().availableProcessors() * 2
    }
}