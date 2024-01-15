package com.jan.belajar.data.repository

import com.jan.belajar.domain.repository.OnboardingRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse

class OnboardingRepositoryImpl(private val api: HttpClient) : OnboardingRepository {
    override suspend fun getOnboarding(): HttpResponse {
        return api.get("0bcd770e-0952-4653-a839-c302ee12c0c8")
    }
}