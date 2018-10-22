package com.test.twitter.domain.repository;

import com.test.twitter.data.local.model.TweetModel;
import com.test.twitter.presentation.screens.main.adapter.TweetDH;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface TweetsRepository {
    Single<List<TweetModel>> getAll();
    Completable add(TweetDH tweets);
    Completable delete(long id);
}
