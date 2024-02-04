package com.example.homework_21_roomfetch.domain.repository

import com.example.homework_21_roomfetch.data.common.Resource
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getCategory(): Flow<List<String>>
}