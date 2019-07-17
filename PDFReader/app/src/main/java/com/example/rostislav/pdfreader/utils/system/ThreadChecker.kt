package com.example.rostislav.pdfreader.utils.system

fun getNumberOfCores(): Int {
    val minThreadsNum = 3
    val deviceThreads = Runtime.getRuntime().availableProcessors()
    return if (minThreadsNum > deviceThreads) minThreadsNum else deviceThreads
}