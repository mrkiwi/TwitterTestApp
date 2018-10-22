package com.test.twitter.presentation.screens.main.favorites;

import com.test.twitter.presentation.core.base.BasePresenterImpl;
import com.test.twitter.presentation.screens.main.adapter.TweetDH;

import javax.inject.Inject;

import io.reactivex.Observable;

public class FavoritesPresenter extends BasePresenterImpl<FavoritesContract.View> implements FavoritesContract.Presenter {

    private FavoritesContract.Model model;

    @Inject
    public FavoritesPresenter(FavoritesContract.View view, FavoritesContract.Model model) {
        super(view);
        this.model = model;
    }

    @Override
    public void subscribe() {
        loadTweets();
    }

    private void loadTweets() {
        addDisposable(model.getFavoritesUseCase.async()
                .flatMap(searchResponse -> Observable.fromIterable(searchResponse)
                        .map(TweetDH::new)
                        .toList())
                .subscribe(result -> {
                    view.setNewData(result);
                }));
    }


    @Override
    public void refresh() {
        compositeDisposable.clear();
        loadTweets();

    }

    @Override
    public void removeFavorite(TweetDH tweetDH) {
        addDisposable(model.removeFavoriteUseCase.async(tweetDH.id)
                .subscribe(() -> {
                    view.favoriteRemoved();
                }));
    }
}
