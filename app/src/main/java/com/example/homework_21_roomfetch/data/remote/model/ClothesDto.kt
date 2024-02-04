package com.example.homework_21_roomfetch.data.remote.model

import com.squareup.moshi.Json

data class ClothesDto(
    @Json(name = "id")
    val id: Int,
    @Json(name = "cover")
    val cover: String,
    @Json(name = "price")
    var price: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "favorite")
    var favorite: Boolean,
    @Json(name = "category")
    var category: String
)
