package com.test.twitter.domain.base;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public abstract class SingleNoInputCase<Out> {

    protected abstract Single<Out> buildTask();

    public Single<Out> sync() {
        return buildTask();
    }

    public Single<Out> async() {
        return buildTask()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
