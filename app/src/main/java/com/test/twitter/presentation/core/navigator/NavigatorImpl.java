package com.test.twitter.presentation.core.navigator;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.test.twitter.presentation.core.base.BaseFragment;

import javax.inject.Inject;

public class NavigatorImpl implements Navigator {

    protected FragmentManager fragmentManager;
    protected int containerId;

    @Inject
    public NavigatorImpl() {
    }

    @Override
    public void bind(FragmentManager fragmentManager, @IdRes int containerId) {
        this.fragmentManager = fragmentManager;
        this.containerId = containerId;
    }

    @Override
    public void unbind() {
        fragmentManager = null;
    }

    @Override
    public void switchFragment(BaseFragment _fragment) {
        switchFragment(_fragment, true);
    }

    @Override
    public void switchFragment(BaseFragment fragment, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(containerId, fragment, fragment.getClass().getName());

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getClass().getName());
        }
        fragmentTransaction.commit();
    }

    @Override
    public BaseFragment getCurrentFragment() {
        return (BaseFragment) fragmentManager.findFragmentById(containerId);
    }

    @Override
    public int getCountFragmentsInBackStack() {
        return fragmentManager.getBackStackEntryCount();
    }

    @Override
    public boolean handleBack() {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
            return true;
        } else {
            return false;
        }
    }
}
