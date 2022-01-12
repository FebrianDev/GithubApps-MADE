package com.febrian.core.core.data.source.remote.network

import com.febrian.core.core.data.source.remote.response.GithubResponse
import com.febrian.core.core.data.source.remote.response.ListGithubResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiService {
    @GET("/users/{username}")
    @Headers("Authorization: token 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")
    fun getDetailUser(
        @Path("username") username: String
    ): Flowable<GithubResponse>

    @GET("/users")
    @Headers("Authorization: token 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")
    fun getUsers(): Flowable<ArrayList<ListGithubResponse>>
}