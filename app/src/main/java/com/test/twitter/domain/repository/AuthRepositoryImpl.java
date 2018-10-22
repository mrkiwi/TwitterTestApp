package com.test.twitter.domain.repository;

import com.test.twitter.data.network.service.AuthService;
import com.test.twitter.presentation.utils.PreferencesManager;

import javax.inject.Inject;

import io.reactivex.Completable;

public class AuthRepositoryImpl implements AuthRepository {

    private AuthService authService;
    private PreferencesManager preferencesManager;

    @Inject
    public AuthRepositoryImpl(AuthService authService, PreferencesManager preferencesManager) {
        this.authService = authService;
        this.preferencesManager = preferencesManager;
    }

    @Override
    public Completable authenticate(String grantType) {
        return authService.authenticate(grantType)
                .flatMapCompletable(authResponse -> {
                    preferencesManager.saveAccessToken(authResponse.accessToken);
                    return Completable.complete();
                });
    }
}
