package com.test.twitter.di.modules;

import com.test.twitter.presentation.screens.main.favorites.FavoritesContract;
import com.test.twitter.presentation.screens.main.favorites.FavoritesFragment;
import com.test.twitter.presentation.screens.main.nearby.NearbyContract;
import com.test.twitter.presentation.screens.main.nearby.NearbyFragment;
import com.test.twitter.presentation.screens.main.search.SearchContract;
import com.test.twitter.presentation.screens.main.search.SearchFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@SuppressWarnings("unused")
@Module
public abstract class FragmentModule {

    @ContributesAndroidInjector(modules = {SearchContract.SearchModule.class})
    abstract SearchFragment searchFragment();

    @ContributesAndroidInjector(modules = {NearbyContract.NearbyModule.class})
    abstract NearbyFragment nearbyFragment();

    @ContributesAndroidInjector(modules = {FavoritesContract.FavoritesModule.class})
    abstract FavoritesFragment favoritesFragment();
}
