package com.test.twitter.domain.repository;

import com.test.twitter.data.local.dao.TweetsDao;
import com.test.twitter.data.local.model.TweetModel;
import com.test.twitter.presentation.screens.main.adapter.TweetDH;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

public class TweetsRepositoryImpl implements TweetsRepository {

    private TweetsDao tweetsDao;

    @Inject
    public TweetsRepositoryImpl(TweetsDao tweetsDao) {
        this.tweetsDao = tweetsDao;
    }


    @Override
    public Single<List<TweetModel>> getAll() {
        return Single.just(tweetsDao.getAll());
    }

    @Override
    public Completable add(TweetDH tweets) {
        return Completable.fromAction(() -> tweetsDao.insertAll(new TweetModel(tweets)));
    }

    @Override
    public Completable delete(long id) {
        return Completable.fromAction(() -> tweetsDao.deleteById(id));
    }
}
