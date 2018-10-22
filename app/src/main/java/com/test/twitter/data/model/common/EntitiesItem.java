package com.test.twitter.data.model.common;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EntitiesItem {
    @SerializedName("hashtags")
    public List<HashTagItem> hashtags;
    @SerializedName("user_mentions")
    public List<MentionItem> mentions;
    @SerializedName("urls")
    public List<URLItem> urls;

    public String getHashTags() {
        StringBuilder result = new StringBuilder();
        for(HashTagItem item : hashtags) {
            String itemStr = item.text.replace(" ", "");
            result.append("#").append(itemStr).append(" ");
        }
        return result.toString();
    }

    public String getMentions() {
        StringBuilder result = new StringBuilder();
        for(MentionItem item : mentions) {
            String itemStr = item.name.replace(" ", "");
            result.append("@").append(itemStr).append(" ");
        }
        return result.toString();
    }
}
