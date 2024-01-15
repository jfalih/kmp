package com.jan.belajar.presentation.features.onboarding

import androidx.lifecycle.ViewModel

class OnboardingViewModel() : ViewModel() {
    val pages = listOf<Onboarding>(
        Onboarding("file:///android_asset/overthink.png", "Focus, relax & ", "sleep", "Just focus and relax for sleep"),
        Onboarding("file:///android_asset/sleep.png", "Be in contact with ", "yourself", "Lorem ipsum dolor sit amet mut lorem sit"),
        Onboarding("file:///android_asset/gaming.png", "Lorem ipsum dolor ", "sit amet", "Lorem ipsum dolor sit amet mut lorem sit")
    )
    val last = pages.size - 1;
}