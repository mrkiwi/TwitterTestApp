package com.test.twitter.domain.repository;

import com.test.twitter.data.model.request.SearchRequest;
import com.test.twitter.data.model.response.SearchResponse;

import io.reactivex.Single;

public interface SearchRepository {
    Single<SearchResponse> search(SearchRequest searchRequest);
}
