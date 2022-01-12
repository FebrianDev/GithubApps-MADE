package com.febrian.githubapp.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.febrian.core.core.data.Resource
import com.febrian.core.core.data.source.remote.response.GithubResponse
import com.febrian.core.core.domain.model.Github
import com.febrian.core.core.domain.usecase.GithubUseCase

class DetailViewModel(private val githubUseCase: GithubUseCase) : ViewModel() {

    fun detailUsers(username: String): LiveData<Resource<GithubResponse>> {
        return LiveDataReactiveStreams.fromPublisher(githubUseCase.getDetailUsers(username))
    }

    fun setFavoriteGithub(github: Github, newStatus: Boolean) =
        githubUseCase.setFavoriteUsers(github, newStatus)

}