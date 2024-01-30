package com.example.homework_21_roomfetch.data.remote.mapper

import com.example.homework_21_roomfetch.data.remote.model.ClothesDto
import com.example.homework_21_roomfetch.domain.model.GetClothes

fun ClothesDto.toDomain(): GetClothes {
    return GetClothes(
        id = id,
        cover = cover,
        price = price,
        title = title,
        favorite = favorite
    )
}
