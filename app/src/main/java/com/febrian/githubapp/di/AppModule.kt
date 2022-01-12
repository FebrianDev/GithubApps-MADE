package com.febrian.githubapp.di

import com.febrian.core.core.domain.usecase.GithubInteractor
import com.febrian.core.core.domain.usecase.GithubUseCase
import com.febrian.githubapp.detail.DetailViewModel
import com.febrian.githubapp.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<GithubUseCase> {
        GithubInteractor(get())
    }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}