package com.example.homework_21_roomfetch.data.remote.repository

import com.example.homework_21_roomfetch.data.common.HandleResponse
import com.example.homework_21_roomfetch.data.common.Resource
import com.example.homework_21_roomfetch.data.remote.mapper.asResource
import com.example.homework_21_roomfetch.data.remote.mapper.toDomain
import com.example.homework_21_roomfetch.data.remote.model.ClothesDto
import com.example.homework_21_roomfetch.data.remote.service.ClothesService
import com.example.homework_21_roomfetch.domain.model.GetClothes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.Response
import javax.inject.Inject

class ClothesRemoteDataStore @Inject constructor(
    private val clothesService: ClothesService,
    private val handleResponse: HandleResponse
) {

    suspend fun getClothes(): Flow<Resource<List<GetClothes>>> {
        return handleResponse.safeApiCall {
            clothesService.getClothes()
        }.asResource { list ->
            list.map {
                it.toDomain()
            }
        }
    }

}
