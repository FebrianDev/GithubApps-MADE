package com.febrian.githubapp.home

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.febrian.core.core.domain.usecase.GithubUseCase

class HomeViewModel(githubUseCase: GithubUseCase) : ViewModel() {
    val github = LiveDataReactiveStreams.fromPublisher(githubUseCase.getAllUsers())
}