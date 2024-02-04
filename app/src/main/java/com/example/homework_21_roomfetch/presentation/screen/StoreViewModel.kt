package com.example.homework_21_roomfetch.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_21_roomfetch.data.common.Resource
import com.example.homework_21_roomfetch.domain.repository.CategoryRepository
import com.example.homework_21_roomfetch.domain.repository.ClothesWrapperRepository
import com.example.homework_21_roomfetch.presentation.event.StoreEvent
import com.example.homework_21_roomfetch.presentation.mapper.toPresenter
import com.example.homework_21_roomfetch.presentation.state.StoreState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(
    private val repository: ClothesWrapperRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    private val _storeState = MutableStateFlow(StoreState())
    val listState: SharedFlow<StoreState> = _storeState.asStateFlow()

    fun onEvent(event: StoreEvent) {
        when (event) {
            is StoreEvent.FetchClothes -> fetchClothes()
            is StoreEvent.FetchCategory -> fetchCategory()
        }
    }

    private fun fetchClothes() {
        viewModelScope.launch {
            repository.getAll().collect { networkResult ->
                when (networkResult) {
                    is Resource.Success -> {
                        val clothes = networkResult.data.map { it.toPresenter() }
                        _storeState.value = StoreState(clothes = clothes)
                    }

                    is Resource.Error -> {
                        _storeState.value =
                            StoreState(errorMessage = "Items not found!")
                    }

                    is Resource.Loading -> {
                        _storeState.value = StoreState(isLoading = networkResult.loading)
                    }
                }
            }
        }
    }

    private fun fetchCategory() {
        viewModelScope.launch {
            categoryRepository.getCategory().collect{
                _storeState.value = StoreState(categories = it)
            }
        }
    }

}
