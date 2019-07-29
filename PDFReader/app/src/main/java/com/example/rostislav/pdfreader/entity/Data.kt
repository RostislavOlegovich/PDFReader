package com.example.rostislav.pdfreader.entity

data class Data(val progress: Long, val url: String, val file: ByteArray?) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Data

        if (progress != other.progress) return false
        if (url != other.url) return false
        if (file != null) {
            if (other.file == null) return false
            if (!file.contentEquals(other.file)) return false
        } else if (other.file != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = progress.hashCode()
        result = 31 * result + url.hashCode()
        result = 31 * result + (file?.contentHashCode() ?: 0)
        return result
    }
}