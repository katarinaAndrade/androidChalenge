package com.katarina.main.ui.di

import com.katarina.main.ui.ui.MainViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val mainUIKoinModule = module {
    viewModelOf(::MainViewModel)
}
