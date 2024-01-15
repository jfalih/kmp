package com.jan.belajar.di

import com.jan.belajar.data.repository.CategoryRepositoryImpl
import com.jan.belajar.domain.repository.CategoryRepository
import com.jan.belajar.domain.use_case.GetCategoryUseCase
import com.jan.belajar.presentation.features.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val categoryModule : Module = module {
    single<CategoryRepository> {
        CategoryRepositoryImpl(get())
    }
    single {
        GetCategoryUseCase(get())
    }
    viewModel {
        HomeViewModel(get())
    }
}

val featureCategoryModule = module {
    includes(appModule, categoryModule)
}