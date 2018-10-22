package com.test.twitter.domain.usecase;

import com.test.twitter.data.model.request.SearchRequest;
import com.test.twitter.data.model.response.SearchResponse;
import com.test.twitter.domain.base.SingleCase;
import com.test.twitter.domain.repository.SearchRepository;

import javax.inject.Inject;

import io.reactivex.Single;

public class SearchUseCase extends SingleCase<SearchRequest, SearchResponse> {

    private SearchRepository searchRepository;

    @Inject
    public SearchUseCase(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    @Override
    protected Single<SearchResponse> buildTask(SearchRequest data) {
        return searchRepository.search(data);
    }
}
