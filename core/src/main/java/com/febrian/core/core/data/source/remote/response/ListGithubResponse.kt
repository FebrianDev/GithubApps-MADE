package com.febrian.core.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListGithubResponse(
    @SerializedName("login")
    var username: String? = null,
    @SerializedName("avatar_url")
    var avatar: String? = null,
    @SerializedName("html_url")
    var url: String? = null,
    @SerializedName("followers_url")
    var followerUrl: String? = null,
    @SerializedName("following_url")
    var followingUrl: String? = null
)
