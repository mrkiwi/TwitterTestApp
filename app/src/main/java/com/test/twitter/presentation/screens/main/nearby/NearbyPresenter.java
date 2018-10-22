package com.test.twitter.presentation.screens.main.nearby;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;

import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.model.LatLng;
import com.patloew.rxlocation.RxLocation;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.test.twitter.data.model.request.SearchRequest;
import com.test.twitter.presentation.core.base.BasePresenterImpl;
import com.test.twitter.presentation.screens.main.adapter.TweetDH;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.Observable;

public class NearbyPresenter extends BasePresenterImpl<NearbyContract.View> implements NearbyContract.Presenter {

    private NearbyContract.Model model;
    private SearchRequest request;

    @Inject
    public NearbyPresenter(NearbyContract.View view, NearbyContract.Model model) {
        super(view);
        this.model = model;
        this.request = new SearchRequest();
        request.count = 10;
        request.byLocation = true;
        request.maxId = 0;
        request.radius = "10km";
        request.query = "";
    }

    @Override
    public void subscribe() {
    }

    @Override
    public void requestPermission(Activity activity) {
        final RxPermissions rxPermissions = new RxPermissions(activity);
        addDisposable(rxPermissions
                .request(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe(granted -> {
                    if (granted) {
                        getCurrentLocation(activity);
                    } else {
                        view.showPermissionDeniedMessage();
                    }
                }));
    }


    @SuppressLint("MissingPermission")
    private void getCurrentLocation(Activity activity) {
        view.showProgress();
        RxLocation rxLocation = new RxLocation(activity);
        LocationRequest locationRequest = LocationRequest.create().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(5000);;
        addDisposable(Maybe.concat(
                rxLocation.location().lastLocation(),
                rxLocation.location().updates(locationRequest).firstElement())
                .map(location -> new LatLng(location.getLatitude(), location.getLongitude()))
                .first(new LatLng(0,0))
                .onErrorReturnItem(new LatLng(0,0))
                .subscribe(item -> {
                            request.location = item;
                            loadTweets();
                        }
                ));
    }


    @Override
    public void refresh() {
        compositeDisposable.clear();
        request.maxId = 0;
        loadTweets();
        view.showProgress();
    }

    @Override
    public void addFavorite(TweetDH tweetDH, boolean add) {
        if(add) {
            addTweetToFavorite(tweetDH);
        } else {
            removeTweetFromFavorite(tweetDH);
        }
    }

    @Override
    public void loadMore() {
        loadTweets();
    }

    private void loadTweets() {
        addDisposable(model.searchUseCase.async(request)
                .flatMap(searchResponse -> {
                    request.maxId = searchResponse.searchMetadata.getNextMaxResults();
                    request.sinceId = searchResponse.searchMetadata.maxId;
                    return Observable.fromIterable(searchResponse.statuses)
                            .map(TweetDH::new)
                            .toList();})
                .subscribe(result -> {
                    if(request.sinceId == 0) {
                        view.setNewData(result);
                    } else {
                        view.addData(result);
                    }
                    if(request.maxId == -1) {
                        view.noMoreData();
                    }
                    view.hideProgress();
                }));
    }

    private void addTweetToFavorite(TweetDH tweetDH) {
        addDisposable(model.addFavoriteUseCase.async(tweetDH)
                .subscribe(() -> {
                    view.favoriteAdded();
                }));
    }

    private void removeTweetFromFavorite(TweetDH tweetDH) {
        addDisposable(model.removeFavoriteUseCase.async(tweetDH.id)
                .subscribe(() -> {
                    view.favoriteRemoved();
                }));
    }
}
