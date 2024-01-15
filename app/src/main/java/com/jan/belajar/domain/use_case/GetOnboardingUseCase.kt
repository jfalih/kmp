package com.jan.belajar.domain.use_case

import com.jan.belajar.common.Resource
import com.jan.belajar.data.remote.dto.onboarding.*
import com.jan.belajar.domain.model.Onboarding
import com.jan.belajar.domain.repository.OnboardingRepository
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class GetOnboardingUseCase(private val repository: OnboardingRepository) {
    operator fun invoke(): Flow<Resource<List<Onboarding>>> = flow {
        try {
            emit(Resource.Loading())
            val body = repository.getOnboarding().body<OnboardingResponseDto>()
            if (body.success) {
                val pages = body.data.map { it -> it.toOnboarding() }
                emit(Resource.Success<List<Onboarding>>(pages))
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