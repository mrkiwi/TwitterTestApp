package com.test.twitter.presentation.screens.main.favorites;

import com.test.twitter.domain.usecase.favorites.GetFavoritesUseCase;
import com.test.twitter.domain.usecase.favorites.RemoveFavoriteUseCase;
import com.test.twitter.presentation.core.base.BasePresenter;
import com.test.twitter.presentation.core.base.BaseView;
import com.test.twitter.presentation.screens.main.adapter.TweetDH;

import java.util.List;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

public interface FavoritesContract {
    interface View extends BaseView {
        void setNewData(List<TweetDH> tweets);
        void favoriteRemoved();
    }

    interface Presenter extends BasePresenter {
        void removeFavorite(TweetDH tweetDH);
        void refresh();
    }

    class Model {
        @Inject
        GetFavoritesUseCase getFavoritesUseCase;

        @Inject
        RemoveFavoriteUseCase removeFavoriteUseCase;

        @Inject
        public Model() {
        }
    }

    @Module
    class FavoritesModule {
        @Provides
        protected View provideView(FavoritesFragment searchFragment) {
            return searchFragment;
        }

        @Provides
        protected Presenter providePresenter(View view, Model model) {
            return new FavoritesPresenter(view, model);
        }
    }
}
