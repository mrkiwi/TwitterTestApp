package com.test.twitter.presentation.core.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.test.twitter.R;
import com.test.twitter.presentation.core.navigator.Navigator;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerAppCompatActivity;


public abstract class BaseActivity<P extends BasePresenter> extends DaggerAppCompatActivity implements BaseView {

    @Nullable
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @Inject
    protected Navigator navigator;

    @Inject
    protected P presenter;
    private Unbinder mUnbinder;

    @LayoutRes
    protected abstract int getLayoutRes();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        mUnbinder = ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        initUtils();
        initUI();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (mUnbinder != null) mUnbinder.unbind();
        if (presenter != null) presenter.unsubscribe();
        if(navigator != null) navigator.unbind();
        super.onDestroy();
    }


    @CallSuper
    protected void initUtils() {
        navigator.bind(getSupportFragmentManager(), R.id.content_frame);
    }

    protected abstract void initUI();

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void setTitle(int title) {
        if (toolbar != null) {
            toolbar.setTitle(title);
        }
    }

    public Navigator getNavigator() {
        return navigator;
    }
}
