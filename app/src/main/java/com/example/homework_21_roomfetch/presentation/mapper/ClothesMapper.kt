package com.example.homework_21_roomfetch.presentation.mapper

import com.example.homework_21_roomfetch.domain.model.GetClothes
import com.example.homework_21_roomfetch.presentation.model.Clothes

fun GetClothes.toPresenter(): Clothes {
    return Clothes(
        id = id,
        cover = cover,
        price = price,
        title = title,
        favorite = favorite,
        category = category
    )
}

fun Clothes.toPresenter(): GetClothes {
    return GetClothes(
        id = id,
        cover = cover,
        price = price,
        title = title,
        favorite = favorite,
        category = category
    )
}
