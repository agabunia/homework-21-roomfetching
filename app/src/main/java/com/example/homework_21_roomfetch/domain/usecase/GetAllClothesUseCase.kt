package com.example.homework_21_roomfetch.domain.usecase

import com.example.homework_21_roomfetch.domain.repository.LocalClothesRepository
import javax.inject.Inject

class GetAllClothesUseCase @Inject constructor(
    private val repository: LocalClothesRepository
) {
    suspend operator fun invoke() = repository.getAll()
}
