package com.febrian.githubapp

import android.app.Application
import com.febrian.core.core.di.databaseModule
import com.febrian.core.core.di.networkModule
import com.febrian.core.core.di.repositoryModule
import com.febrian.githubapp.di.useCaseModule
import com.febrian.githubapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}