package com.example.rostislav.pdfreader.utils.system

object ThreadChecker {
    private const val MIN_THREADS_NUM = 3

    fun getNumberOfCores(): Int {
        val deviceThreads = Runtime.getRuntime().availableProcessors()
        return if (MIN_THREADS_NUM > deviceThreads) MIN_THREADS_NUM else deviceThreads
    }
}