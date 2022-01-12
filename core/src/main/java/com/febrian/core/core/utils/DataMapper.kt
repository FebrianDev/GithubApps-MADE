package com.febrian.core.core.utils

import com.febrian.core.core.data.source.local.entity.GithubEntity
import com.febrian.core.core.data.source.remote.response.GithubResponse
import com.febrian.core.core.data.source.remote.response.ListGithubResponse
import com.febrian.core.core.domain.model.Github

object DataMapper {

    //    List Response to Entities
    fun mapResponsesToEntities(input: List<ListGithubResponse>): List<GithubEntity> {
        val githubList = ArrayList<GithubEntity>()
        input.map {
            val github = GithubEntity(
                avatar = it.avatar,
                username = it.username,
                followerUrl = it.followerUrl,
                followingUrl = it.followingUrl,
                url = it.url,
                isFavorite = false

            )

            githubList.add(github)
        }

        return githubList
    }

    fun mapEntitiesToDomain(input: List<GithubEntity>): List<Github> =
        input.map {
            Github(
                name = it.name,
                company = it.company,
                location = it.location,
                following = it.following,
                follower = it.follower,
                avatar = it.avatar,
                repository = it.repository,
                username = it.username,
                followerUrl = it.followerUrl,
                followingUrl = it.followingUrl,
                url = it.url,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(it: Github) = GithubEntity(
        name = it.name,
        company = it.company,
        location = it.location,
        following = it.following,
        follower = it.follower,
        avatar = it.avatar,
        repository = it.repository,
        username = it.username,
        followerUrl = it.followerUrl,
        followingUrl = it.followingUrl,
        url = it.url,
        isFavorite = false
    )

    fun mapEntityToDomain(it: GithubEntity): Github {
        return Github(
            name = it.name,
            company = it.company,
            location = it.location,
            following = it.following,
            follower = it.follower,
            avatar = it.avatar,
            repository = it.repository,
            username = it.username,
            followerUrl = it.followerUrl,
            followingUrl = it.followingUrl,
            url = it.url,
            isFavorite = it.isFavorite
        )
    }

    fun mapResponseToEntity(it: GithubResponse): GithubEntity {
        return GithubEntity(
            name = it.name,
            company = it.company,
            location = it.location,
            following = it.following,
            follower = it.follower,
            avatar = it.avatar,
            repository = it.repository,
            username = it.username,
            followerUrl = it.followerUrl,
            followingUrl = it.followingUrl,
            url = it.url,
            isFavorite = false
        )
    }

}