package com.liebxsantos.pexelswallpapersapp.ui.extensions

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView
import java.io.ByteArrayOutputStream

fun ImageView.getBitmap(): Bitmap? {
    return (this.drawable as? BitmapDrawable)?.bitmap
}

fun Bitmap.toByteArray(): ByteArray {
    val outputStream = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.JPEG, 80, outputStream)
    return outputStream.toByteArray()
}