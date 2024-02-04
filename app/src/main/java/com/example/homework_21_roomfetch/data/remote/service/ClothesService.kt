package com.example.homework_21_roomfetch.data.remote.service

import com.example.homework_21_roomfetch.data.remote.model.ClothesDto
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET

interface ClothesService {
    @GET("https://run.mocky.io/v3/df8d4951-2757-45aa-8f60-bf1592a090ce")
    suspend fun getClothes(): Response<List<ClothesDto>>
}
