package com.example.homework_21_roomfetch.data.local.datasource

import com.example.homework_21_roomfetch.data.common.HandleResponse
import com.example.homework_21_roomfetch.data.common.Resource
import com.example.homework_21_roomfetch.data.local.dao.ClothesDao
import com.example.homework_21_roomfetch.data.local.entity.ClothesEntity
import com.example.homework_21_roomfetch.data.remote.mapper.asResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ClothesLocalDataSource @Inject constructor(
    private val clothesDao: ClothesDao
) {

    fun getAll(): Flow<List<ClothesEntity>> {
        return clothesDao.getAll()
    }

    suspend fun insertAll(clothes: List<ClothesEntity>) {
        clothesDao.insertAll(clothes)
    }

}
