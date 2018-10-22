package com.test.twitter.di.modules;

import android.content.Context;

import com.test.twitter.presentation.utils.PreferencesManager;
import com.test.twitter.presentation.utils.PreferencesManagerImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PreferencesModule {
    @Singleton
    @Provides
    public PreferencesManager providePreferencesManager(final Context context) {
        return new PreferencesManagerImpl(context);
    }
}
