package com.febrian.core.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.febrian.core.core.data.source.local.entity.GithubEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface GithubDao {
    @Query("SELECT * FROM github")
    fun getAllUsers(): Flowable<List<GithubEntity>>

    @Query("SELECT * FROM github where isFavorite = 1")
    fun getFavoriteUsers(): Flowable<List<GithubEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(github: List<GithubEntity>): Completable

    @Query("UPDATE github SET isFavorite = :isFavorite WHERE username = :username")
    fun updateFavoriteUser(isFavorite: Boolean, username: String)

    @Query("SELECT * FROM github where username = :username")
    fun getDetailUsers(username: String): Flowable<GithubEntity>
}