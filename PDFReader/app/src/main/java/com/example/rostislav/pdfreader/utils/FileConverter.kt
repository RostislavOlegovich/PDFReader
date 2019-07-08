package com.example.rostislav.pdfreader.utils

import androidx.room.TypeConverter
import java.io.File

class FileConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun fromFile(file: File) = file.readText()

        @TypeConverter
        @JvmStatic
        fun toFile(string: String?): File {
            val f = File("file")
            string?.let { f.writeText(it) }
            return f
        }
    }
}