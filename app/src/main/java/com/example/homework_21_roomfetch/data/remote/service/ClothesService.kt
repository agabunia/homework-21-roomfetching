package com.example.homework_21_roomfetch.data.remote.service

import com.example.homework_21_roomfetch.data.remote.model.ClothesDto
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET

interface ClothesService {
    @GET("https://run.mocky.io/v3/1775d634-92dc-4c32-ae71-1707b8cfee41")
    suspend fun getClothes(): Response<List<ClothesDto>>
}
