package com.example.homework_21_roomfetch.di

import com.example.homework_21_roomfetch.data.local.dao.ClothesDao
import com.example.homework_21_roomfetch.data.local.datasource.ClothesLocalDataSource
import com.example.homework_21_roomfetch.data.local.repository.CategoryRepositoryImpl
import com.example.homework_21_roomfetch.data.remote.datasource.ClothesRemoteDataSource
import com.example.homework_21_roomfetch.data.repository.ClothesWrapperRepositoryImpl
import com.example.homework_21_roomfetch.domain.repository.CategoryRepository
import com.example.homework_21_roomfetch.domain.repository.ClothesWrapperRepository
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
    fun provideWrapperClothesRepository(
        clothesRemoteDataStore: ClothesRemoteDataSource,
        clothesLocalDataStore: ClothesLocalDataSource,
    ): ClothesWrapperRepository {
        return ClothesWrapperRepositoryImpl(
            clothesLocalDataStore = clothesLocalDataStore,
            clothesRemoteDataStore = clothesRemoteDataStore,
        )
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(
        clothesDao: ClothesDao
    ): CategoryRepository {
        return CategoryRepositoryImpl(
            clothesDao = clothesDao
        )
    }
}
