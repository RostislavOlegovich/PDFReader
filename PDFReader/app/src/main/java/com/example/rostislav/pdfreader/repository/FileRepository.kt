package com.example.rostislav.pdfreader.repository

import com.example.rostislav.pdfreader.core.interfaces.Repository
import com.example.rostislav.pdfreader.core.observer.Observable
import com.example.rostislav.pdfreader.core.observer.Observer
import com.example.rostislav.pdfreader.entity.Data
import com.example.rostislav.pdfreader.entity.FileData

interface FileRepository : Repository {
    fun loadFile(url: String): FileData

    fun loadAllFiles(): List<FileData>

    fun getObservableNetwork(): Observable<Data, Observer<Data>>

    fun getObservableDatabase(): Observable<FileData, Observer<FileData>>
}