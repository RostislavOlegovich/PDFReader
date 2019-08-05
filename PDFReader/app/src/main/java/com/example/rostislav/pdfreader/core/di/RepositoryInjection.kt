package com.example.rostislav.pdfreader.core.di

import android.content.Context
import com.example.rostislav.pdfreader.entity.exception.NoFileException
import com.example.rostislav.pdfreader.repository.FileRepository
import com.example.rostislav.pdfreader.repository.FileRepositoryImpl

object RepositoryInjection {
    fun provideFileRepository(context: Context): FileRepository {
        return FileRepositoryImpl(
            context,
            ManagersInjection.get(),
            ManagersInjection.get(),
            ManagersInjection.get()
        )
    }

    inline fun <reified T> create(context: Context): T {
        return when (T::class.java) {
            FileRepository::class.java -> provideFileRepository(context) as T
            else -> throw NoFileException()
        }
    }
}