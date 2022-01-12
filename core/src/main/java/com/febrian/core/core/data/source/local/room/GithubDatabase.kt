package com.febrian.core.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.febrian.core.core.data.source.local.entity.GithubEntity

@Database(entities = [GithubEntity::class], version = 1, exportSchema = false)
abstract class GithubDatabase : RoomDatabase() {
    abstract fun githubDao(): GithubDao
}