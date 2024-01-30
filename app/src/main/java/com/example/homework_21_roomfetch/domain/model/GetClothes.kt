package com.example.homework_21_roomfetch.domain.model

data class GetClothes(
    val id: Int,
    val cover: String,
    var price: String,
    val title: String,
    var favorite: Boolean
)
