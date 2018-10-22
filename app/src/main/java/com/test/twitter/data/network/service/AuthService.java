package com.test.twitter.data.network.service;

import com.test.twitter.data.model.response.AuthResponse;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AuthService {

    @FormUrlEncoded
    @POST("oauth2/token")
    Single<AuthResponse> authenticate(@Field("grant_type") String grantType);

}
