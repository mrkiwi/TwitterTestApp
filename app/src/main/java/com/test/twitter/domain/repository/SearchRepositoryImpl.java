package com.test.twitter.domain.repository;

import com.test.twitter.data.model.request.SearchRequest;
import com.test.twitter.data.model.response.SearchResponse;
import com.test.twitter.data.network.service.CommonApiService;

import javax.inject.Inject;

import io.reactivex.Single;

public class SearchRepositoryImpl implements SearchRepository {
    private CommonApiService commonApiService;

    @Inject
    public SearchRepositoryImpl(CommonApiService commonApiService) {
        this.commonApiService = commonApiService;
    }


    @Override
    public Single<SearchResponse> search(SearchRequest searchRequest) {
        String geocode = "";
        if(searchRequest.byLocation) {
            geocode = searchRequest.location.latitude + "," + searchRequest.location.longitude + "," + searchRequest.radius;
        }
        return commonApiService.search(searchRequest.query, searchRequest.maxId, searchRequest.count, geocode);
    }
}
