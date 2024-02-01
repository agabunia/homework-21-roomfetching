package com.example.homework_21_roomfetch.data.local.repository

import com.example.homework_21_roomfetch.data.local.dao.ClothesDao
import com.example.homework_21_roomfetch.data.local.entity.ClothesEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ClothesLocalDataStore @Inject constructor(
    private val clothesDao: ClothesDao
) {

    fun getAll(): Flow<List<ClothesEntity>> {
        return clothesDao.getAll().map { clothes ->
            clothes
        }
    }

    suspend fun insertAll(clothes: List<ClothesEntity>) {
        clothesDao.insertAll(clothes)
    }

}
