package com.test.twitter.data.model.common;

import android.net.Uri;

import com.google.gson.annotations.SerializedName;

public class MetadataItem {
    @SerializedName("max_id")
    public Long maxId;
    @SerializedName("max_id_str")
    public String maxIdStr;
    @SerializedName("next_results")
    public String nextResults;
    public int count;
    @SerializedName("refresh_url")
    public String refreshUrl;

    public long getNextMaxResults() {
        if(nextResults == null) return -1;
        Uri uri= Uri.parse(nextResults);
        return Long.parseLong(uri.getQueryParameter("max_id"));
    }
}
