package com.test.twitter.di;
import com.test.twitter.di.modules.AppModule;
import com.test.twitter.presentation.TwitterTestApp;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AppModule.class, AndroidSupportInjectionModule.class
})
public interface AppComponent extends AndroidInjector<TwitterTestApp> {
    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<TwitterTestApp> {

    }
}
