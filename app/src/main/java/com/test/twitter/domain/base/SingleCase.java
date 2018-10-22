package com.test.twitter.domain.base;

import java.util.concurrent.Executors;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public abstract class SingleCase<In, Out> {

    protected abstract Single<Out> buildTask(In data);

    public Single<Out> sync(In data) {
        return buildTask(data);
    }

    public Single<Out> async(In data) {
        return buildTask(data)
                .subscribeOn(Schedulers.from(Executors.newFixedThreadPool(30)))
                .observeOn(AndroidSchedulers.mainThread());
    }

}
