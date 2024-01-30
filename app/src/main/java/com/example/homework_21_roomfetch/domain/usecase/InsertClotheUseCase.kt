package com.example.homework_21_roomfetch.domain.usecase

import com.example.homework_21_roomfetch.domain.model.GetClothes
import com.example.homework_21_roomfetch.domain.repository.LocalClothesRepository
import javax.inject.Inject

class InsertClotheUseCase @Inject constructor(
    private val repository: LocalClothesRepository
) {
    suspend operator fun invoke(clothes: List<GetClothes>) {
        repository.insertAll(clothes)
    }
}