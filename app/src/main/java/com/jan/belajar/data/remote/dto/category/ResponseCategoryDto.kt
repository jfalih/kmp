package com.jan.belajar.data.remote.dto.category

import kotlinx.serialization.Serializable

@Serializable
data class ResponseCategoryDto(
    val data: List<CategoryDto>,
    val messages: String,
    val success: Boolean
)