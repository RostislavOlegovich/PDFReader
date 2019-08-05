package com.example.rostislav.pdfreader.core.di

import android.content.Context
import com.example.rostislav.pdfreader.core.base.BaseManager
import com.example.rostislav.pdfreader.model.database.Database
import com.example.rostislav.pdfreader.model.database.DatabaseManager
import com.example.rostislav.pdfreader.model.file.FileManager
import com.example.rostislav.pdfreader.model.file.FileManagerImpl
import com.example.rostislav.pdfreader.model.network.Network
import com.example.rostislav.pdfreader.model.network.NetworkManager
import com.example.rostislav.pdfreader.model.presenter.PresenterManager
import com.example.rostislav.pdfreader.model.presenter.PresenterManagerImpl

object ManagersInjection {
    val managers = mutableListOf<BaseManager>()

    fun setup(context: Context) {
        managers.add(PresenterManagerImpl() as PresenterManager)
        managers.add(FileManagerImpl(context) as FileManager)
        managers.add(NetworkManager(context) as Network)
        managers.add(DatabaseManager(context) as Database)
    }

    inline fun <reified T> get() = managers.first { it is T } as T
}