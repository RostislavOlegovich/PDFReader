package com.example.rostislav.pdfreader.repository

import com.example.rostislav.pdfreader.core.observer.Observable
import com.example.rostislav.pdfreader.core.observer.Observer
import com.example.rostislav.pdfreader.entity.Data
import com.example.rostislav.pdfreader.entity.FileData
import java.io.File

interface FileRepository {
    fun read(localPath: String): File

    fun write(byteArray: ByteArray, filename: String, url: String)

    fun download(url: String)

    fun getAllData(): List<FileData>

    fun getObservable(): Observable<Data, Observer<Data>>
}