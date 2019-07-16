package com.example.rostislav.pdfreader.feature.main

import android.content.Context
import com.example.rostislav.pdfreader.core.base.BasePresenter
import com.example.rostislav.pdfreader.utils.extention.getNameFromString
import okhttp3.Response
import java.io.File

class MainPresenter(context: Context) : BasePresenter<View>(context), Presenter {

    override fun downloadView(url: String, context: Context, filename: String) {
        doAsync(
                { network.downloadFromNetwork(url, context) },
                { writeToStorage(it, context, filename) },
                this::onError
        )
        view?.openActivity(filename)
    }

    override fun getFromDatabase(key: String, context: Context) {
        val filename = key.getNameFromString()
        val storage = readFromStorage(filename, context).exists()
        if (storage) {
            view?.openActivity(filename)
        } else {
            downloadView(key, context, filename)
        }
    }

    override fun getAll() {
        executor.execute {
            val list = database.getAll()
            handler.post { view?.showView(list) }
        }
    }

    override fun readFromStorage(filename: String, context: Context): File {
        return fileManager.readFileFromInternalStorage(filename, context)
    }

    override fun writeToStorage(response: Response, context: Context, filename: String) {
        fileManager.writeFileToInternalStorage(context, response, filename)
    }
}