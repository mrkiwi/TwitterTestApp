package com.test.twitter.domain.repository;

import io.reactivex.Completable;

public interface AuthRepository {
    Completable authenticate(String grantType);
}
