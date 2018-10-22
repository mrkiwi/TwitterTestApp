package com.test.twitter.presentation.core.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.test.twitter.presentation.core.navigator.Navigator;
import com.test.twitter.presentation.utils.ProgressHolder;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;

public abstract class BaseFragment<P extends BasePresenter> extends DaggerFragment implements BaseFragmentView {

    protected BaseActivity activity;
    protected Unbinder mUnbinder;

    private ProgressHolder progressHolder;

    private boolean isMenuEnabled = true;

    @Inject
    protected P presenter;

    @LayoutRes
    protected abstract int getLayoutResource();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            activity = (BaseActivity) context;
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("BaseFragment should be in BaseActivity");
        }
        setHasOptionsMenu(isMenuEnabled);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResource(), container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUtils();
        initUI();
    }

    protected void setTitle(int title) {
        activity.setTitle(title);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        presenter.unsubscribe();
        mUnbinder.unbind();
        super.onDestroyView();
    }

    protected Navigator getNavigator() {
        return activity.getNavigator();
    }
    public abstract void initUI();

    @CallSuper
    protected void initUtils() {
        progressHolder = new ProgressHolder(Objects.requireNonNull(getView()));
    }

    public void setProgressContentView(View view){
        progressHolder.setContentView(view);
    }

    @Override
    public void showProgress() {
        progressHolder.show();
    }

    @Override
    public void hideProgress() {
        progressHolder.hide();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

}
