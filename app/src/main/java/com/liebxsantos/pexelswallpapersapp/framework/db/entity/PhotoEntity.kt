package com.liebxsantos.pexelswallpapersapp.framework.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photo_table")
data class PhotoEntity(
    val id: Int,
    val photo: String
)
