package com.febrian.core.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Github(
    var username: String? = null,
    var name: String? = null,
    var avatar: String? = null,
    var company: String? = null,
    var location: String? = null,
    var repository: Int = 0,
    var follower: Int = 0,
    var following: Int = 0,
    var url: String? = null,
    var followerUrl: String? = null,
    var followingUrl: String? = null,
    val isFavorite: Boolean
) : Parcelable