package com.febrian.core.core.domain.usecase

import com.febrian.core.core.data.Resource
import com.febrian.core.core.data.source.remote.response.GithubResponse
import com.febrian.core.core.domain.model.Github
import com.febrian.core.core.domain.repository.IGithubRepository
import io.reactivex.Flowable

class GithubInteractor(private val githubRepository: IGithubRepository) : GithubUseCase {
    override fun getAllUsers(): Flowable<Resource<List<Github>>> {
        return githubRepository.getAllUsers()
    }

    override fun getFavoriteUsers(): Flowable<List<Github>> {
        return githubRepository.getFavoriteUsers()
    }

    override fun setFavoriteUsers(github: Github, state: Boolean) {
        githubRepository.setFavoriteUsers(github, state)
    }

    override fun getDetailUsers(username: String): Flowable<Resource<GithubResponse>> {
        return githubRepository.getDetailUsers(username)
    }

}