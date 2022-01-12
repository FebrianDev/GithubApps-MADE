package com.febrian.core.core.data.source.local

import com.febrian.core.core.data.source.local.entity.GithubEntity
import com.febrian.core.core.data.source.local.room.GithubDao
import io.reactivex.Flowable

class LocalDataSource(private val githubDao: GithubDao) {
    fun getAllUsers(): Flowable<List<GithubEntity>> = githubDao.getAllUsers()

    fun getFavoriteUsers(): Flowable<List<GithubEntity>> = githubDao.getFavoriteUsers()

    fun insertUsers(githubList: List<GithubEntity>) = githubDao.insert(githubList)

    fun setFavoriteUsers(github: GithubEntity, newState: Boolean) {
        github.isFavorite = newState
        githubDao.updateFavoriteUser(github.isFavorite, github.username.toString())
    }
}