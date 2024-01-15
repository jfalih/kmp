package com.jan.belajar.data.repository

import com.jan.belajar.domain.repository.CategoryRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse

class CategoryRepositoryImpl(val api: HttpClient): CategoryRepository {
    override suspend fun getCategories(): HttpResponse {
        return api.get("home/features")
    }

    override suspend fun getCategoryById(id: Int): Any {
        TODO("Not yet implemented")
    }
}