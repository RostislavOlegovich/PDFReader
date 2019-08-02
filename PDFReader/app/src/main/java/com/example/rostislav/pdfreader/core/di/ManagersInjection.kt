package com.example.rostislav.pdfreader.core.di

import android.content.Context
import com.example.rostislav.pdfreader.model.database.DatabaseManager
import com.example.rostislav.pdfreader.model.file.FileManagerImpl
import com.example.rostislav.pdfreader.model.network.NetworkManager
import com.example.rostislav.pdfreader.model.presenter.PresenterManagerImpl
import com.example.rostislav.pdfreader.utils.system.getNumberOfCores
import java.util.concurrent.Executors

object ManagersInjection {
    val managers = mutableListOf<Any>()

    fun setup(context: Context) {
        managers.add(Executors.newFixedThreadPool(getNumberOfCores()))
        managers.add(PresenterManagerImpl())
        managers.add(FileManagerImpl(context))
        managers.add(NetworkManager(context))
        managers.add(DatabaseManager(context))
    }

    inline fun <reified T> get(): T {
        return managers.first { it.javaClass == T::class.java } as T
    }
}