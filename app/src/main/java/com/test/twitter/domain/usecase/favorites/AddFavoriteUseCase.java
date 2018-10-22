package com.test.twitter.domain.usecase.favorites;

import com.test.twitter.domain.base.CompletableCase;
import com.test.twitter.domain.repository.TweetsRepository;
import com.test.twitter.presentation.screens.main.adapter.TweetDH;

import javax.inject.Inject;

import io.reactivex.Completable;

public class AddFavoriteUseCase extends CompletableCase<TweetDH> {

    private TweetsRepository tweetsRepository;

    @Inject
    public AddFavoriteUseCase(TweetsRepository tweetsRepository) {
        this.tweetsRepository = tweetsRepository;
    }

    @Override
    protected Completable buildTask(TweetDH data) {
        return tweetsRepository.add(data);
    }
}
