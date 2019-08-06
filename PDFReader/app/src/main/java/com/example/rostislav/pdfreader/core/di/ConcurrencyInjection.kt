package com.example.rostislav.pdfreader.core.di

import android.os.Handler
import android.os.Looper
import com.example.rostislav.pdfreader.utils.system.ThreadChecker
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

object ConcurrencyInjection {
    lateinit var executors: ExecutorService
    lateinit var handler: Handler

    fun setup() {
        executors = Executors.newFixedThreadPool(ThreadChecker.getNumberOfCores())
        handler = Handler(Looper.getMainLooper())
    }
}