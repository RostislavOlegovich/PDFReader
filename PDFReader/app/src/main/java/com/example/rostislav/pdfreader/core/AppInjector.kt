package com.example.rostislav.pdfreader.core

import android.content.Context
import com.example.rostislav.pdfreader.core.di.ConcurrencyInjection
import com.example.rostislav.pdfreader.core.di.ManagersInjection

object AppInjector {
    fun setup(context: Context) {
        ManagersInjection.setup(context)
        ConcurrencyInjection.setup()
    }
}