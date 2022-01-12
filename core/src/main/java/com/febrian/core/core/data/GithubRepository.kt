package com.febrian.core.core.data

import com.febrian.core.core.data.source.remote.RemoteDataSource
import com.febrian.core.core.data.source.remote.network.ApiResponse
import com.febrian.core.core.data.source.remote.response.GithubResponse
import com.febrian.core.core.data.source.remote.response.ListGithubResponse
import com.febrian.core.core.domain.model.Github
import com.febrian.core.core.domain.repository.IGithubRepository
import com.febrian.core.core.utils.AppExecutors
import com.febrian.core.core.utils.DataMapper
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GithubRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: com.febrian.core.core.data.source.local.LocalDataSource,
    private val appExecutors: AppExecutors
) : IGithubRepository {
    override fun getAllUsers(): Flowable<Resource<List<Github>>> {
        return object :
            com.febrian.core.core.data.NetworkBoundResource<List<Github>, List<ListGithubResponse>>(
                appExecutors
            ) {
            override fun loadFromDB(): Flowable<List<Github>> {
                return localDataSource.getAllUsers().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Github>?): Boolean = data == null || data.isEmpty()

            override fun createCall(): Flowable<ApiResponse<List<ListGithubResponse>>> =
                remoteDataSource.getAllUsers()

            override fun saveCallResult(data: List<ListGithubResponse>) {
                val githubList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertUsers(githubList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }
        }.asFlowable()
    }

    override fun getFavoriteUsers(): Flowable<List<Github>> {
        return localDataSource.getFavoriteUsers().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteUsers(github: Github, state: Boolean) {
        val githubEntity = DataMapper.mapDomainToEntity(github)
        appExecutors.diskIO().execute { localDataSource.setFavoriteUsers(githubEntity, state) }
    }

    override fun getDetailUsers(username: String): Flowable<Resource<GithubResponse>> {
        return remoteDataSource.getDetail(username)
    }
}