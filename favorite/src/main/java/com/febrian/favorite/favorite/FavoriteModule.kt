package com.febrian.favorite.favorite

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val module = module {
    viewModel { FavoriteViewModel(get()) }
}