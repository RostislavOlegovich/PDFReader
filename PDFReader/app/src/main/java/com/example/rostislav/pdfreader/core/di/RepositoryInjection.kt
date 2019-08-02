package com.example.rostislav.pdfreader.core.di

import android.content.Context
import com.example.rostislav.pdfreader.model.database.DatabaseManager
import com.example.rostislav.pdfreader.model.file.FileManagerImpl
import com.example.rostislav.pdfreader.model.network.NetworkManager
import com.example.rostislav.pdfreader.repository.FileRepository
import com.example.rostislav.pdfreader.repository.FileRepositoryImpl

object RepositoryInjection {
    fun provideRepository(context: Context): FileRepository {
        return FileRepositoryImpl(
            context,
            ManagersInjection.get<NetworkManager>(),
            ManagersInjection.get<FileManagerImpl>(),
            ManagersInjection.get<DatabaseManager>()
        )
    }

    inline fun <reified T> create(context: Context): T = provideRepository(context) as T
}