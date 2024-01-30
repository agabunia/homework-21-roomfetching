package com.example.homework_21_roomfetch.domain.repository

import com.example.homework_21_roomfetch.domain.model.GetClothes
import kotlinx.coroutines.flow.Flow

interface LocalClothesRepository {

    fun getAll(): Flow<List<GetClothes>>

    suspend fun insertAll(clothes: List<GetClothes>)

    suspend fun delete(clothes: List<GetClothes>)

}
