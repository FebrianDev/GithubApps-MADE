package com.febrian.favorite.favorite

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.febrian.core.core.domain.usecase.GithubUseCase

class FavoriteViewModel(githubUseCase : GithubUseCase) : ViewModel() {
    val favorite = LiveDataReactiveStreams.fromPublisher(githubUseCase.getFavoriteUsers())
}