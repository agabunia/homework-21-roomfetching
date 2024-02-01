package com.example.homework_21_roomfetch.di

import com.example.homework_21_roomfetch.data.local.repository.ClothesLocalDataStore
import com.example.homework_21_roomfetch.data.remote.repository.ClothesRemoteDataStore
import com.example.homework_21_roomfetch.data.repository.ClothesWrapperRepositoryImpl
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
        clothesRemoteDataStore: ClothesRemoteDataStore,
        clothesLocalDataStore: ClothesLocalDataStore,
    ): ClothesWrapperRepository {
        return ClothesWrapperRepositoryImpl(
            clothesLocalDataStore = clothesLocalDataStore,
            clothesRemoteDataStore = clothesRemoteDataStore,
        )
    }
}
