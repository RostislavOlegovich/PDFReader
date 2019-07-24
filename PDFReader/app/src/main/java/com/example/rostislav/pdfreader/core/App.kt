package com.example.rostislav.pdfreader.core

import android.app.Application
import com.example.rostislav.pdfreader.model.presenter.PresenterManager
import com.example.rostislav.pdfreader.model.presenter.PresenterManagerImpl
import com.example.rostislav.pdfreader.repository.Repository
import com.example.rostislav.pdfreader.repository.RepositoryImpl
import com.example.rostislav.pdfreader.utils.system.getNumberOfCores
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class App : Application() {

    lateinit var repository: Repository
    lateinit var presenterManager: PresenterManager

    val executor: ExecutorService =
        Executors.newFixedThreadPool(getNumberOfCores())

    override fun onCreate() {
        super.onCreate()
        repository = RepositoryImpl(applicationContext)
        presenterManager = PresenterManagerImpl()
    }
}