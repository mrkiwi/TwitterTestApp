package com.test.twitter.presentation.screens.main.nearby;

import android.app.Activity;

import com.test.twitter.domain.usecase.SearchUseCase;
import com.test.twitter.domain.usecase.favorites.AddFavoriteUseCase;
import com.test.twitter.domain.usecase.favorites.RemoveFavoriteUseCase;
import com.test.twitter.presentation.core.base.BaseFragmentView;
import com.test.twitter.presentation.core.base.BasePresenter;
import com.test.twitter.presentation.screens.main.adapter.TweetDH;

import java.util.List;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

public interface NearbyContract {
    interface View extends BaseFragmentView {
        void showPermissionDeniedMessage();
        void setNewData(List<TweetDH> tweets);
        void addData(List<TweetDH> tweets);
        void noMoreData();
        void favoriteAdded();
        void favoriteRemoved();
    }

    interface Presenter extends BasePresenter {
        void requestPermission(Activity activity);
        void refresh();
        void loadMore();
        void addFavorite(TweetDH tweetDH, boolean add);
    }

    class Model {

        @Inject
        SearchUseCase searchUseCase;

        @Inject
        AddFavoriteUseCase addFavoriteUseCase;

        @Inject
        RemoveFavoriteUseCase removeFavoriteUseCase;

        @Inject
        public Model() {
        }
    }

    @Module
    class NearbyModule {
        @Provides
        protected View provideView(NearbyFragment searchFragment) {
            return searchFragment;
        }
        @Provides
        protected Presenter providePresenter(View view, Model model) {
            return new NearbyPresenter(view, model);
        }
    }
}
