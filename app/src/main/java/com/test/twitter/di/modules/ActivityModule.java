package com.test.twitter.di.modules;

import com.test.twitter.presentation.screens.main.MainActivity;
import com.test.twitter.presentation.screens.main.MainContract;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector(modules = {MainContract.MainActivityModule.class})
    public abstract MainActivity mainActivity();
}
