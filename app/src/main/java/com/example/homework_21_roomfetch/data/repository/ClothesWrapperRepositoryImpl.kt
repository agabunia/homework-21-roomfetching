package com.example.homework_21_roomfetch.data.repository

import com.example.homework_21_roomfetch.data.common.Resource
import com.example.homework_21_roomfetch.data.local.mapper.toData
import com.example.homework_21_roomfetch.data.local.mapper.toDomain
import com.example.homework_21_roomfetch.data.local.datasource.ClothesLocalDataSource
import com.example.homework_21_roomfetch.data.remote.datasource.ClothesRemoteDataSource
import com.example.homework_21_roomfetch.domain.model.GetClothes
import com.example.homework_21_roomfetch.domain.repository.ClothesWrapperRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ClothesWrapperRepositoryImpl @Inject constructor(
    private val clothesRemoteDataStore: ClothesRemoteDataSource,
    private val clothesLocalDataStore: ClothesLocalDataSource
) : ClothesWrapperRepository {

    override fun getAll(): Flow<Resource<List<GetClothes>>> {
        return flow {
            clothesRemoteDataStore.getClothes().collect { remoteDataResult ->
                when (remoteDataResult) {
                    is Resource.Success -> {
                        clothesLocalDataStore.insertAll(remoteDataResult.data.map { it.toData() })
                        emit(
                            Resource.Success(
                                clothesLocalDataStore.getAll().first().map { it.toDomain() })
                        )
                    }

                    is Resource.Error -> {
                        val localData = clothesLocalDataStore.getAll().map {
                            it.map { it.toDomain() }
                        }.first()

                        if (localData.isNotEmpty()) {
                            emit(Resource.Success(localData))
                        } else {
                            emit(Resource.Error(errorMessage = "Items not found"))
                        }
                    }

                    is Resource.Loading -> {
                        emit(Resource.Loading(remoteDataResult.loading))
                    }
                }
            }
        }
    }

}
