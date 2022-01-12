package com.febrian.core.core.domain.repository

import com.febrian.core.core.data.Resource
import com.febrian.core.core.data.source.remote.response.GithubResponse
import com.febrian.core.core.domain.model.Github
import io.reactivex.Flowable

interface IGithubRepository {
    fun getAllUsers(): Flowable<Resource<List<Github>>>

    fun getFavoriteUsers(): Flowable<List<Github>>

    fun setFavoriteUsers(github: Github, state: Boolean)

    fun getDetailUsers(username: String): Flowable<Resource<GithubResponse>>
}