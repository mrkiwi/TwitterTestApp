package com.test.twitter.data.model.response;

import com.google.gson.annotations.SerializedName;
import com.test.twitter.data.model.common.MetadataItem;
import com.test.twitter.data.model.common.StatusItem;

import java.util.List;

public class SearchResponse {
    @SerializedName("statuses")
    public List<StatusItem> statuses;
    @SerializedName("search_metadata")
    public MetadataItem searchMetadata;
}
