package com.jan.belajar.presentation.features.home

import com.jan.belajar.domain.model.Category

data class HomeState(
    val loading: Boolean = false,
    val categories: List<Category> = emptyList(),
    val error: String = ""
)
