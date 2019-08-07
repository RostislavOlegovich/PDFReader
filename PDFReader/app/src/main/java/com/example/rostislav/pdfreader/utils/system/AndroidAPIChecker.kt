package com.example.rostislav.pdfreader.utils.system

import android.os.Build

object AndroidAPIChecker {
    //куда лучше перенести - в объект BooleanUtils или оставить тут?
    fun checkAndroidAPI() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
}