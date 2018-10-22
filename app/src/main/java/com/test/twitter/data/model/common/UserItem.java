package com.test.twitter.data.model.common;

import com.google.gson.annotations.SerializedName;

public class UserItem {
    public Long id;
    @SerializedName("id_str")
    public String idStr;
    @SerializedName("screen_name")
    public String screenName;
    public String name;
    @SerializedName("profile_image_url")
    public String profileImageUrl;
}
