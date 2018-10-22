package com.test.twitter.presentation.utils;

public interface PreferencesManager {
    void saveAccessToken(String _accessToken);
    String getAccessToken();
    boolean isAccessTokenExist();
}
