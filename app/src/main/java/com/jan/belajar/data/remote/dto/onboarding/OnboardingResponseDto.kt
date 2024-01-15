package com.jan.belajar.data.remote.dto.onboarding

import kotlinx.serialization.Serializable

@Serializable
data class OnboardingResponseDto(
    val success: Boolean,
    val data: List<OnboardingDto>
)