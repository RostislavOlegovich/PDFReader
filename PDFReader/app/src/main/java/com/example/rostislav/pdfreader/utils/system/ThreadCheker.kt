package com.example.rostislav.pdfreader.utils.system

object ThreadCheker {
    fun getNumberOfCores(): Int {
        return Runtime.getRuntime().availableProcessors()
    }
}