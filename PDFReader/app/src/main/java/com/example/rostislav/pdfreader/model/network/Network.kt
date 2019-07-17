package com.example.rostislav.pdfreader.model.network

interface Network {
    fun downloadFromNetwork(url: String): ByteArray
}