package com.example.homework_21_roomfetch.data.repository

import com.example.homework_21_roomfetch.data.common.Resource
import com.example.homework_21_roomfetch.data.local.mapper.toData
import com.example.homework_21_roomfetch.data.local.mapper.toDomain
import com.example.homework_21_roomfetch.data.local.repository.ClothesLocalDataStore
import com.example.homework_21_roomfetch.data.remote.repository.ClothesRemoteDataStore
import com.example.homework_21_roomfetch.domain.model.GetClothes
import com.example.homework_21_roomfetch.domain.repository.ClothesWrapperRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ClothesWrapperRepositoryImpl @Inject constructor(
    private val clothesRemoteDataStore: ClothesRemoteDataStore,
    private val clothesLocalDataStore: ClothesLocalDataStore
) : ClothesWrapperRepository {

    override suspend fun getAll(): Flow<Resource<List<GetClothes>>> {
        return flow {
            clothesRemoteDataStore.getClothes().collect { remoteDataResult ->
                when (remoteDataResult) {
                    is Resource.Success -> {
                        clothesLocalDataStore.insertAll(remoteDataResult.data.map { it.toData() })
                        emit(Resource.Success(remoteDataResult.data))
                    }

                    is Resource.Error -> {
                        val localData = clothesLocalDataStore.getAll().map {
                            it.map { it.toDomain() }
                        }.first()
                        if (localData.isNotEmpty()) {
                            emit(Resource.Success(localData))
                        } else {
                            emit(Resource.Error(remoteDataResult.errorMessage))
                        }
                    }

                    is Resource.Loading -> {
                        emit(Resource.Loading(remoteDataResult.loading))
                    }
                }
            }

            if (clothesLocalDataStore.getAll().first().isEmpty()) {
                emit(Resource.Success(clothesLocalDataStore.getAll().map {
                    it.map {
                        it.toDomain()
                    }
                }.first()))
            }
        }
    }

    override suspend fun insertAll(clothes: List<GetClothes>) {
        clothesLocalDataStore.insertAll(clothes.map { it.toData() })
    }

}
