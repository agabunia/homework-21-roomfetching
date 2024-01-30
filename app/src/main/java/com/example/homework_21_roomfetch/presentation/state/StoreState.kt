package com.example.homework_21_roomfetch.presentation.state

import com.example.homework_21_roomfetch.presentation.model.Clothes

data class StoreState(
    val clothes: List<Clothes>? = null,
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)
