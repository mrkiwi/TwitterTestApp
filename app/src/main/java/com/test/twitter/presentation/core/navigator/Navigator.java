package com.test.twitter.presentation.core.navigator;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;

import com.test.twitter.presentation.core.base.BaseFragment;

public interface Navigator {
    void bind(final FragmentManager fragmentManager, @IdRes final int containerId);

    void unbind();

    void switchFragment(final BaseFragment _fragment);

    void switchFragment(final BaseFragment _fragment, boolean _addToBackStack);

    BaseFragment getCurrentFragment();

    int getCountFragmentsInBackStack();

    boolean handleBack();
}
