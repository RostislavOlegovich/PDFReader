package com.example.rostislav.pdfreader.core.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.rostislav.pdfreader.core.App
import com.example.rostislav.pdfreader.feature.main.Presenter

open class BaseActivity : AppCompatActivity() {
    lateinit var presenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = (applicationContext as App).presenter
    }
}
