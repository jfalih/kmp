package com.jan.belajar.presentation.features.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jan.belajar.common.Resource
import com.jan.belajar.domain.model.Category
import com.jan.belajar.domain.use_case.GetCategoryUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HomeViewModel(private val getCategoryUseCase: GetCategoryUseCase) : ViewModel() {
    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    init {
        getCategories()
    }

    private fun getCategories() {
        getCategoryUseCase().onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = HomeState(categories = it.data ?: emptyList())
                }

                is Resource.Error -> {
                    _state.value = HomeState(error = "Error something")
                }

                is Resource.Loading -> {
                    _state.value = HomeState(loading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}

class HomeViewModelPreview() : ViewModel() {
    private val _state = mutableStateOf(
        HomeState(
            categories = listOf(
                Category(
                    id = 1,
                    title = "Halo",
                    color = "0xFF",
                    type = "music",
                    min = 5
                ),
                Category(
                    id = 1,
                    title = "Halo",
                    color = "0xFF",
                    type = "music",
                    min = 5
                )
            )
        )
    )
    val state: State<HomeState> = _state
}