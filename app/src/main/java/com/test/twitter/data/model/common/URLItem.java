package com.test.twitter.data.model.common;

import com.google.gson.annotations.SerializedName;

public class URLItem {
    public String url;
    @SerializedName("expanded_url")
    public String expandedUrl;
    @SerializedName("display_url")
    public String displayUrl;
}
