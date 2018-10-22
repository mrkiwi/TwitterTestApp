package com.test.twitter.domain.usecase;

import com.test.twitter.domain.base.CompletableCase;
import com.test.twitter.domain.repository.AuthRepository;

import javax.inject.Inject;

import io.reactivex.Completable;

public class AuthenticateUseCase extends CompletableCase<String> {

    private AuthRepository authRepository;

    @Inject
    public AuthenticateUseCase(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    protected Completable buildTask(String grantType) {
        return authRepository.authenticate(grantType);
    }
}
