package com.test.twitter.domain.usecase.favorites;

import com.test.twitter.data.local.model.TweetModel;
import com.test.twitter.domain.base.SingleNoInputCase;
import com.test.twitter.domain.repository.TweetsRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class GetFavoritesUseCase extends SingleNoInputCase<List<TweetModel>> {

    private TweetsRepository tweetsRepository;

    @Inject
    public GetFavoritesUseCase(TweetsRepository tweetsRepository) {
        this.tweetsRepository = tweetsRepository;
    }

    @Override
    protected Single<List<TweetModel>> buildTask() {
        return tweetsRepository.getAll();
    }
}
