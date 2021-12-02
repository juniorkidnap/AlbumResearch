package com.whities.albumresearch.di

import com.whities.albumresearch.business.navigation.AppNavigation
import com.whities.albumresearch.business.navigation.AppNavigationImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
abstract class NavigationModule {

    @Binds
    abstract fun bindNavigator(impl: AppNavigationImpl): AppNavigation
}