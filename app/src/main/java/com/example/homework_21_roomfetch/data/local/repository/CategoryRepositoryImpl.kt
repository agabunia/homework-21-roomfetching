package com.example.homework_21_roomfetch.data.local.repository

import com.example.homework_21_roomfetch.data.local.dao.ClothesDao
import com.example.homework_21_roomfetch.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val clothesDao: ClothesDao
): CategoryRepository {

    override fun getCategory(): Flow<List<String>> {
        return clothesDao.getAllCategory()
    }

}