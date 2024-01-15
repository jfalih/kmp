package com.jan.belajar.data.remote.dto.onboarding

import com.jan.belajar.domain.model.Onboarding
import kotlinx.serialization.Serializable


@Serializable
data class OnboardingDto(
    val description: String,
    val id: Int,
    val title: String,
    val url: String
)

fun OnboardingDto.toOnboarding(): Onboarding {
    return Onboarding(
        id = id,
        title = title,
        description = description,
        url = url,
    )
}