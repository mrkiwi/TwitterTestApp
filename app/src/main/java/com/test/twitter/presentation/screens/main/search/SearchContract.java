package com.test.twitter.presentation.screens.main.search;

import android.support.v7.widget.SearchView;

import com.test.twitter.domain.usecase.SearchUseCase;
import com.test.twitter.domain.usecase.favorites.AddFavoriteUseCase;
import com.test.twitter.domain.usecase.favorites.RemoveFavoriteUseCase;
import com.test.twitter.presentation.core.base.BasePresenter;
import com.test.twitter.presentation.core.base.BaseView;
import com.test.twitter.presentation.screens.main.adapter.TweetDH;

import java.util.List;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

public interface SearchContract {
    interface View extends BaseView {
        void setNewData(List<TweetDH> tweets);
        void addData(List<TweetDH> tweets);
        void noMoreData();
        void favoriteAdded();
        void favoriteRemoved();
        void stopRefreshing();
    }

    interface Presenter extends BasePresenter {
        void subscribeSearchView(SearchView searchView);
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
    class SearchModule {
        @Provides
        protected View provideView(SearchFragment searchFragment) {
            return searchFragment;
        }
        @Provides
        protected Presenter providePresenter(View view, Model model) {
            return new SearchPresenter(view, model);
        }
    }
}
