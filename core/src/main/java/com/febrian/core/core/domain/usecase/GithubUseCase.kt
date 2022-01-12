package com.febrian.core.core.domain.usecase

import com.febrian.core.core.data.Resource
import com.febrian.core.core.data.source.remote.response.GithubResponse
import com.febrian.core.core.domain.model.Github
import io.reactivex.Flowable

interface GithubUseCase {
    fun getAllUsers(): Flowable<Resource<List<Github>>>
    fun getFavoriteUsers(): Flowable<List<Github>>
    fun setFavoriteUsers(github: Github, state: Boolean)
    fun getDetailUsers(username: String): Flowable<Resource<GithubResponse>>
}
