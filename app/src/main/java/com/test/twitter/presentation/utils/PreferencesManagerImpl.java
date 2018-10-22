package com.test.twitter.presentation.utils;

import android.content.Context;

import com.orhanobut.hawk.Hawk;
import com.test.twitter.data.Constants;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PreferencesManagerImpl implements PreferencesManager {

    @Inject
    public PreferencesManagerImpl(Context _context) {
        Hawk.init(_context).build();
    }

    @Override
    public void saveAccessToken(String _accessToken) {
        Hawk.put(Constants.ACCESS_TOKEN, _accessToken);
    }

    @Override
    public String getAccessToken() {
        return Hawk.get(Constants.ACCESS_TOKEN);
    }

    @Override
    public boolean isAccessTokenExist() {
        return getAccessToken() != null;
    }
}
