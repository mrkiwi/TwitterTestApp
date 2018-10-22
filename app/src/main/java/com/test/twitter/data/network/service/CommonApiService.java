package com.test.twitter.data.network.service;

import com.test.twitter.data.model.response.SearchResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CommonApiService {
    @GET("1.1/search/tweets.json")
    Single<SearchResponse> search(@Query("q") String q, @Query("max_id") long maxId, @Query("count") int count, @Query("geocode") String geocode);
}
