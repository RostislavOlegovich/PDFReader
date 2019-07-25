package com.example.rostislav.pdfreader.core.base

import android.content.Context
import com.example.rostislav.pdfreader.model.database.Database
import com.example.rostislav.pdfreader.model.database.DatabaseManager
import com.example.rostislav.pdfreader.model.file.FileManager
import com.example.rostislav.pdfreader.model.file.FileManagerImpl
import com.example.rostislav.pdfreader.model.network.Network
import com.example.rostislav.pdfreader.model.network.NetworkManager

abstract class BaseRepository(context: Context) {
    val network: Network = NetworkManager()
    val database: Database = DatabaseManager(context)
    val fileManager: FileManager = FileManagerImpl(context)
}