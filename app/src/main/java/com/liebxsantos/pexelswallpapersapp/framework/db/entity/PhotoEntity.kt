package com.liebxsantos.pexelswallpapersapp.framework.db.entity

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.liebxsantos.core.data.DbConstants
import com.liebxsantos.core.domain.model.PhotoDomain
import com.liebxsantos.core.domain.model.SrcDomain

@Entity(tableName = DbConstants.PHOTO_TABLE_NAME)
data class PhotoEntity(
    @PrimaryKey
    val id: Int,
    val urlPhoto: String,
    val photographer: String,
    val imageData: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as PhotoEntity
        return imageData.contentEquals(other.imageData)
    }

    override fun hashCode(): Int {
        return imageData.contentHashCode()
    }
}
fun List<PhotoEntity>.toPhotoDomain() = map {
    PhotoDomain(
        id = it.id,
        photographer = it.photographer,
        imageBytes = it.imageData,
        srcDomain = SrcDomain(original = it.urlPhoto)
    )
}

fun ByteArray.byteArrayToBitmap(data: ByteArray): Bitmap {
    return BitmapFactory.decodeByteArray(data, 0, data.size)
}

