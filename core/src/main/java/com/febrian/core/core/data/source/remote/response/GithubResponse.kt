package com.febrian.core.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GithubResponse(
    @SerializedName("login")
    var username: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("avatar_url")
    var avatar: String? = null,
    @SerializedName("company")
    var company: String? = null,
    @SerializedName("location")
    var location: String? = null,
    @SerializedName("public_repos")
    var repository: Int = 0,
    @SerializedName("followers")
    var follower: Int = 0,
    @SerializedName("following")
    var following: Int = 0,
    @SerializedName("html_url")
    var url: String? = null,
    @SerializedName("followers_url")
    var followerUrl: String? = null,
    @SerializedName("following_url")
    var followingUrl: String? = null
)