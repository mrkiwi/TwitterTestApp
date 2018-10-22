package com.test.twitter.domain.usecase.favorites;

import com.test.twitter.domain.base.CompletableCase;
import com.test.twitter.domain.repository.TweetsRepository;

import javax.inject.Inject;

import io.reactivex.Completable;

public class RemoveFavoriteUseCase extends CompletableCase<Long> {

    private TweetsRepository tweetsRepository;

    @Inject
    public RemoveFavoriteUseCase(TweetsRepository tweetsRepository) {
        this.tweetsRepository = tweetsRepository;
    }

    @Override
    protected Completable buildTask(Long id) {
        return tweetsRepository.delete(id);
    }
}
