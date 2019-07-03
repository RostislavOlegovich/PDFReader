package com.example.rostislav.pdfreader.core.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.rostislav.pdfreader.core.App
import com.example.rostislav.pdfreader.model.network.Network

open class BaseActivity : AppCompatActivity() {

    lateinit var network: Network

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        network = (applicationContext as App).network
    }
}
