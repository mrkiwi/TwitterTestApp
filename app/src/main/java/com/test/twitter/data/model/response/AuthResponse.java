package com.test.twitter.data.model.response;

import com.google.gson.annotations.SerializedName;

public class AuthResponse {
    @SerializedName("token_type")
    public String tokenType;
    @SerializedName("access_token")
    public String accessToken;
}
