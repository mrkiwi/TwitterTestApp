package com.test.twitter.presentation.screens.main.search;

import android.support.v7.widget.SearchView;

import com.test.twitter.data.model.request.SearchRequest;
import com.test.twitter.presentation.core.base.BasePresenterImpl;
import com.test.twitter.presentation.screens.main.adapter.TweetDH;
import com.test.twitter.presentation.utils.RxSearchObservable;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;

public class SearchPresenter extends BasePresenterImpl<SearchContract.View> implements SearchContract.Presenter {

    private SearchContract.Model model;
    private SearchRequest request;

    @Inject
    SearchPresenter(SearchContract.View view, SearchContract.Model model) {
        super(view);
        this.model = model;
        this.request = new SearchRequest();
        request.count = 10;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void subscribeSearchView(SearchView searchView) {
        addDisposable(RxSearchObservable.fromView(searchView)
                .debounce(300, TimeUnit.MILLISECONDS)
                .filter(text -> !text.isEmpty())
                .distinctUntilChanged()
                .subscribe(s -> {
                    request.query = s;
                    request.maxId = 0;
                    loadTweets();
                }));
    }

    @Override
    public void refresh() {
        if(request.query != null && !request.query.isEmpty()) {
            request.maxId = 0;
            loadTweets();
        }
        view.stopRefreshing();
    }

    @Override
    public void loadMore() {
        loadTweets();
    }

    @Override
    public void addFavorite(TweetDH tweetDH, boolean add) {
        if(add) {
            addTweetToFavorite(tweetDH);
        } else {
            removeTweetFromFavorite(tweetDH);
        }
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
