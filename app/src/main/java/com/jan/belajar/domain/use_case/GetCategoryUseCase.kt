package com.jan.belajar.domain.use_case

import com.jan.belajar.common.Resource
import com.jan.belajar.data.remote.dto.category.ResponseCategoryDto
import com.jan.belajar.data.remote.dto.category.toCategory
import com.jan.belajar.domain.model.Category
import com.jan.belajar.domain.model.Onboarding
import com.jan.belajar.domain.repository.CategoryRepository
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCategoryUseCase(private val repository: CategoryRepository) {
    operator fun invoke(): Flow<Resource<List<Category>>> = flow {
        try {
            emit(Resource.Loading())
            val body = repository.getCategories().body<ResponseCategoryDto>()
            if (body.success) {
                val categories = body.data.map { it -> it.toCategory() }
                emit(Resource.Success<List<Category>>(categories))
            } else {
                emit(Resource.Error("error"))
            }
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            emit(Resource.Error(e.response.status.description))
        } catch (e: ClientRequestException) {
            // 4xx - responses
            emit(Resource.Error(e.response.status.description))
        }
    }
}