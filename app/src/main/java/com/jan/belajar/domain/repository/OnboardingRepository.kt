package com.jan.belajar.domain.repository

import io.ktor.client.statement.HttpResponse

interface OnboardingRepository {
    suspend fun getOnboarding(): HttpResponse
}