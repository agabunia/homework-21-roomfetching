package com.example.homework_21_roomfetch.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ClothesEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "cover") val cover: String,
    @ColumnInfo(name = "price") val price: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "favorite") var favorite: Boolean
)
