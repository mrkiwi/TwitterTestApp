package com.test.twitter.presentation.screens.main;

import com.test.twitter.domain.usecase.AuthenticateUseCase;
import com.test.twitter.presentation.core.base.BasePresenter;
import com.test.twitter.presentation.core.base.BaseView;
import com.test.twitter.presentation.utils.PreferencesManager;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

public interface MainContract {

    interface View extends BaseView {
        void loadSearchFragment();
    }
    interface Presenter extends BasePresenter { }

    class Model {
        @Inject
        AuthenticateUseCase authenticateUseCase;

        @Inject
        PreferencesManager preferencesManager;

        @Inject
        public Model() {
        }
    }

    @Module
    class MainActivityModule {
        @Provides
        protected MainContract.View provideView(MainActivity mainActivity) {
            return mainActivity;
        }

        @Provides
        protected MainContract.Presenter providePresenter(MainContract.View view, MainContract.Model model) {
            return new MainPresenter(view, model);
        }
    }

}
