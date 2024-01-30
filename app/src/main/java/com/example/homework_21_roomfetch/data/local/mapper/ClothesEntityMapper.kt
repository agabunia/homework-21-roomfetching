package com.example.homework_21_roomfetch.data.local.mapper

import com.example.homework_21_roomfetch.data.local.entity.ClothesEntity
import com.example.homework_21_roomfetch.domain.model.GetClothes

fun ClothesEntity.toDomain(): GetClothes {
    return GetClothes(
        id = id,
        cover = cover,
        price = price,
        title = title,
        favorite = favorite
    )
}


