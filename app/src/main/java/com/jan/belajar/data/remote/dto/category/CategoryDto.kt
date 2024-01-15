package com.jan.belajar.data.remote.dto.category

import com.jan.belajar.domain.model.Category
import kotlinx.serialization.Serializable

@Serializable
data class CategoryDto(
    val color: String,
    val id: Int,
    val min: Int,
    val title: String,
    val type: String
)

fun CategoryDto.toCategory() : Category {
    return Category(
        id = id,
        color = color,
        min = min,
        title = title,
        type = type,
    )
}