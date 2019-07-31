package com.example.rostislav.pdfreader.entity

data class Data(val progress: Long, val url: String, val byteArray: ByteArray?) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Data

        if (progress != other.progress) return false
        if (url != other.url) return false
        if (byteArray != null) {
            if (other.byteArray == null) return false
            if (!byteArray.contentEquals(other.byteArray)) return false
        } else if (other.byteArray != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = progress.hashCode()
        result = 31 * result + url.hashCode()
        result = 31 * result + (byteArray?.contentHashCode() ?: 0)
        return result
    }
}