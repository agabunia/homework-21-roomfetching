package com.example.homework_21_roomfetch.domain.repository

import com.example.homework_21_roomfetch.data.common.Resource
import com.example.homework_21_roomfetch.domain.model.GetClothes
import kotlinx.coroutines.flow.Flow

interface ClothesWrapperRepository {

    fun getAll(): Flow<Resource<List<GetClothes>>>

}
