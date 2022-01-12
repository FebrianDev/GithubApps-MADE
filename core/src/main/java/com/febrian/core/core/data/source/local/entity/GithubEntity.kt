package com.febrian.core.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "github")
data class GithubEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int = 0,
    var username: String? = null,
    @ColumnInfo(name = "name")
    var name: String? = null,
    @ColumnInfo(name = "avatar")
    var avatar: String? = null,
    @ColumnInfo(name = "company")
    var company: String? = null,
    @ColumnInfo(name = "location")
    var location: String? = null,
    @ColumnInfo(name = "repository")
    var repository: Int = 0,
    @ColumnInfo(name = "follower")
    var follower: Int = 0,
    @ColumnInfo(name = "following")
    var following: Int = 0,
    @ColumnInfo(name = "url")
    var url: String? = null,
    @ColumnInfo(name = "follower_url")
    var followerUrl: String? = null,
    @ColumnInfo(name = "following_url")
    var followingUrl: String? = null,
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)