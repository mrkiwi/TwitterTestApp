package com.test.twitter.data.model.request;

import com.google.android.gms.maps.model.LatLng;

public class SearchRequest {
    public boolean byLocation;
    public String query;
    public String resultType;
    public int count;
    public long maxId;
    public long sinceId;
    public LatLng location;
    public String radius;
}
