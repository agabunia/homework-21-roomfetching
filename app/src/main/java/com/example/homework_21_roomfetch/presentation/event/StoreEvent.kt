package com.example.homework_21_roomfetch.presentation.event

sealed class StoreEvent {
    object FetchClothes: StoreEvent()
    object FetchCategory: StoreEvent()
}
