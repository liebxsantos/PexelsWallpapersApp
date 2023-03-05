package com.liebxsantos.pexelswallpapersapp.framework.downloader

interface Downloader {
    fun downloadFile(url: String, description: String): Long
}