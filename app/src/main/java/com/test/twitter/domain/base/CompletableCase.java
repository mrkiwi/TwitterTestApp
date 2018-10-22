package com.test.twitter.domain.base;


import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public abstract class CompletableCase<In> {

    protected abstract Completable buildTask(In data);

    public Completable sync(In data) {
        return buildTask(data);
    }

    public Completable async(In data) {
        return buildTask(data)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
