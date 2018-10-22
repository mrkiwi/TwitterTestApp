package com.test.twitter.di.modules;

import android.content.Context;

import com.test.twitter.presentation.TwitterTestApp;

import dagger.Binds;
import dagger.Module;

@Module(includes = {
        NavigationModule.class,
        ActivityModule.class,
        FragmentModule.class,
        RepositoryModule.class,
        NetworkModule.class,
        PreferencesModule.class,
        DatabaseModule.class
})
public abstract class AppModule {
    @Binds
    abstract Context context(final TwitterTestApp twitterTestApp);
}
