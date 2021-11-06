package com.example.androidbp.di

import com.example.androidbp.screens.home.HomeContract
import com.example.androidbp.screens.home.HomePresenterImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class MVPModules {

    @Binds
    abstract fun bindMainPresenter(
        mainPresenterImpl: HomePresenterImpl
    ): HomeContract.HomePresenter
}