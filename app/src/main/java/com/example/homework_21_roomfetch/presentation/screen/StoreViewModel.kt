package com.example.homework_21_roomfetch.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_21_roomfetch.data.common.Resource
import com.example.homework_21_roomfetch.presentation.event.StoreEvent
import com.example.homework_21_roomfetch.domain.repository.ClothesRepository
import com.example.homework_21_roomfetch.domain.usecase.GetAllClothesUseCase
import com.example.homework_21_roomfetch.domain.usecase.InsertClotheUseCase
import com.example.homework_21_roomfetch.presentation.mapper.toPresenter
import com.example.homework_21_roomfetch.presentation.state.StoreState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(
    private val repository: ClothesRepository,
    private val getAllClothesUseCase: GetAllClothesUseCase,
    private val insertClotheUseCase: InsertClotheUseCase
) : ViewModel() {

    private val _storeState = MutableStateFlow(StoreState())
    val listState: SharedFlow<StoreState> = _storeState.asStateFlow()

    fun onEvent(event: StoreEvent) {
        when (event) {
            is StoreEvent.FetchClothes -> fetchClothes()
        }
    }

    private fun fetchClothes() {
        viewModelScope.launch {
            repository.getClothes().collect { networkResult ->
                when (networkResult) {
                    is Resource.Success -> {
                        val clothes = networkResult.data.map { it.toPresenter() }
                        _storeState.value = StoreState(clothes = clothes)
                        insertClotheUseCase(networkResult.data)
                    }

                    is Resource.Error -> {
                        val localClothes = getAllClothesUseCase().firstOrNull()
                        if (!localClothes.isNullOrEmpty()) {
                            _storeState.value = StoreState(clothes = localClothes.map {
                                it.toPresenter()
                            })
                        } else {
                            _storeState.value =
                                StoreState(errorMessage = "Items not found!")
                        }
                    }

                    is Resource.Loading -> {
                        _storeState.value = StoreState(isLoading = networkResult.loading)
                    }
                }
            }
        }
    }

}
