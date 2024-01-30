package com.example.homework_21_roomfetch.data.repository

import com.example.homework_21_roomfetch.data.common.HandleResponse
import com.example.homework_21_roomfetch.data.common.Resource
import com.example.homework_21_roomfetch.data.remote.mapper.asResource
import com.example.homework_21_roomfetch.data.remote.mapper.toDomain
import com.example.homework_21_roomfetch.data.remote.service.ClothesService
import com.example.homework_21_roomfetch.domain.model.GetClothes
import com.example.homework_21_roomfetch.domain.repository.ClothesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ClothesRepositoryImpl @Inject constructor(
    private val clothesService: ClothesService,
    private val handleResponse: HandleResponse
) : ClothesRepository {
    override suspend fun getClothes(): Flow<Resource<List<GetClothes>>> {
        return handleResponse.safeApiCall {
            clothesService.getClothes()
        }.asResource { list ->
            list.map { clothesDto ->
                clothesDto.toDomain()
            }
        }
    }
}
