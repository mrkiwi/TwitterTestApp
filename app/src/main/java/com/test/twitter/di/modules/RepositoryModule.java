package com.test.twitter.di.modules;

import com.test.twitter.domain.repository.AuthRepository;
import com.test.twitter.domain.repository.AuthRepositoryImpl;
import com.test.twitter.domain.repository.SearchRepository;
import com.test.twitter.domain.repository.SearchRepositoryImpl;
import com.test.twitter.domain.repository.TweetsRepository;
import com.test.twitter.domain.repository.TweetsRepositoryImpl;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class RepositoryModule {
    @Binds
    abstract AuthRepository authRepository(AuthRepositoryImpl authRepositoryImpl);

    @Binds
    abstract SearchRepository searchRepository(SearchRepositoryImpl searchRepositoryImpl);

    @Binds
    abstract TweetsRepository challengeRepository(TweetsRepositoryImpl repository);
}
