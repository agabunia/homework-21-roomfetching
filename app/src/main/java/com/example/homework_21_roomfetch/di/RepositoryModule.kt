package com.example.homework_21_roomfetch.di

import com.example.homework_21_roomfetch.data.common.HandleResponse
import com.example.homework_21_roomfetch.data.local.dao.ClothesDao
import com.example.homework_21_roomfetch.data.repository.ClothesRepositoryImpl
import com.example.homework_21_roomfetch.data.remote.service.ClothesService
import com.example.homework_21_roomfetch.data.repository.LocalClothesRepositoryImpl
import com.example.homework_21_roomfetch.domain.repository.ClothesRepository
import com.example.homework_21_roomfetch.domain.repository.LocalClothesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideClothesRepository(
        clothesService: ClothesService,
        handleResponse: HandleResponse
    ): ClothesRepository {
        return ClothesRepositoryImpl(
            clothesService = clothesService,
            handleResponse = handleResponse
        )
    }

    @Provides
    @Singleton
    fun provideLocalClothesDatabaseRepository(
        clothesDao: ClothesDao
    ): LocalClothesRepository {
        return LocalClothesRepositoryImpl(clothesDao = clothesDao)
    }
}
