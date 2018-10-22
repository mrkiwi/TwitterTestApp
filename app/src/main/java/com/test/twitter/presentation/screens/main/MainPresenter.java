package com.test.twitter.presentation.screens.main;

import com.test.twitter.presentation.core.base.BasePresenterImpl;

import javax.inject.Inject;

public class MainPresenter extends BasePresenterImpl<MainContract.View> implements MainContract.Presenter  {

    private MainContract.Model model;

    @Inject
    public MainPresenter(MainContract.View view, MainContract.Model model) {
        super(view);
        this.model = model;
    }

    @Override
    public void subscribe() {
        if(!model.preferencesManager.isAccessTokenExist()) {
            addDisposable(model.authenticateUseCase.async("client_credentials").subscribe(this::authSuccess));
        } else {
            authSuccess();
        }
    }

    private void authSuccess() {
        view.loadSearchFragment();
    }
}
