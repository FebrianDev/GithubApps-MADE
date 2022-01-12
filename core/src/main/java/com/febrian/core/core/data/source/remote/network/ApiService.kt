package com.febrian.core.core.data.source.remote.network

import com.febrian.core.core.data.source.remote.response.GithubResponse
import com.febrian.core.core.data.source.remote.response.ListGithubResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiService {
    @GET("/users")
    @Headers("Authorization: token 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")
    fun getUsers(): Flowable<ArrayList<ListGithubResponse>>

    @GET("/users/{username}")
    @Headers("Authorization: token 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")
    fun getDetailUser(
        @Path("username") username: String
    ): Flowable<GithubResponse>
}