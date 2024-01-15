package com.jan.belajar.domain.repository

import io.ktor.client.statement.HttpResponse

interface CategoryRepository {
    suspend fun getCategories(): HttpResponse
    suspend fun getCategoryById(id: Int): Any
}