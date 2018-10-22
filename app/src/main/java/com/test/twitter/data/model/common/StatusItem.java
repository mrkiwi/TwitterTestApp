package com.test.twitter.data.model.common;

import com.google.gson.annotations.SerializedName;

public class StatusItem {
    @SerializedName("created_at")
    public String createdAt;
    public Long id;
    @SerializedName("id_str")
    public String idStr;
    public String text;
    @SerializedName("entities")
    public EntitiesItem entities;
    @SerializedName("user")
    public UserItem user;

}
