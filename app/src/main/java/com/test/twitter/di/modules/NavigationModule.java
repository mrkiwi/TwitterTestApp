package com.test.twitter.di.modules;

import com.test.twitter.presentation.core.navigator.Navigator;
import com.test.twitter.presentation.core.navigator.NavigatorImpl;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;


@Singleton
@Module
public abstract class NavigationModule {

    @Binds
    abstract Navigator provideNavigator(NavigatorImpl nav);

}
