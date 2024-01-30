package com.example.homework_21_roomfetch.data.repository

import com.example.homework_21_roomfetch.data.local.dao.ClothesDao
import com.example.homework_21_roomfetch.data.local.mapper.toData
import com.example.homework_21_roomfetch.data.local.mapper.toDomain
import com.example.homework_21_roomfetch.domain.model.GetClothes
import com.example.homework_21_roomfetch.domain.repository.LocalClothesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalClothesRepositoryImpl @Inject constructor(
    private val clothesDao: ClothesDao
) : LocalClothesRepository {

    override fun getAll(): Flow<List<GetClothes>> {
        return clothesDao.getAll().map { clothes ->
            clothes.map {
                it.toDomain()
            }
        }
    }

    override suspend fun insertAll(clothes: List<GetClothes>) {
        clothesDao.insertAll(clothes.map {
            it.toData()
        })
    }

    override suspend fun delete(clothes: List<GetClothes>) {
        clothesDao.delete(clothes.map {
            it.toData()
        })
    }

}
