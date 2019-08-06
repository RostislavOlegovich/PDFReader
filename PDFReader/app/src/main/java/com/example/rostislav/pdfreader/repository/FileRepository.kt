package com.example.rostislav.pdfreader.repository

import com.example.rostislav.pdfreader.core.interfaces.Repository
import com.example.rostislav.pdfreader.core.observer.Observable
import com.example.rostislav.pdfreader.core.observer.Observer
import com.example.rostislav.pdfreader.entity.Data
import com.example.rostislav.pdfreader.entity.FileData
import java.io.File

interface FileRepository : Repository {
    fun loadFile(url: String): File

    fun loadAllFiles(): List<FileData>

    fun getObservable(): Observable<Data, Observer<Data>>
}