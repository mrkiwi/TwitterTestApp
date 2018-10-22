package com.test.twitter.presentation.screens.main.search;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.ViewGroup;

import com.luseen.autolinklibrary.AutoLinkMode;
import com.test.twitter.R;
import com.test.twitter.data.Constants;
import com.test.twitter.presentation.core.base.BaseFragment;
import com.test.twitter.presentation.screens.main.adapter.TweetAdapter;
import com.test.twitter.presentation.screens.main.adapter.TweetDH;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SearchFragment extends BaseFragment<SearchContract.Presenter> implements SearchContract.View, TweetAdapter.TweetClickListener  {

    @BindView(R.id.rv_tweets)
    RecyclerView rvTweets;
    @BindView(R.id.srl_tweets)
    SwipeRefreshLayout srlTweets;
    @BindView(R.id.sv_search)
    SearchView svSearch;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    public static SearchFragment newInstance(String searchWord)
    {
        Bundle args = new Bundle();
        args.putString(Constants.ARG_SEARCH_WORD, searchWord);
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private TweetAdapter mTweetAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_search;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.subscribe();
        setHasOptionsMenu(true);
    }

    @Override
    public void initUI() {
        setTitle(R.string.title_search);
        presenter.subscribeSearchView(svSearch);
        rvTweets.setLayoutManager(new LinearLayoutManager(getContext()));
        mTweetAdapter = new TweetAdapter(R.layout.item_tweet, new ArrayList<>());
        srlTweets.setOnRefreshListener(() -> {
            presenter.refresh();
        });
        rvTweets.setAdapter(mTweetAdapter);
        mTweetAdapter.setOnLoadMoreListener(() -> presenter.loadMore(), rvTweets);
        mTweetAdapter.setListener(this);
        mTweetAdapter.setEmptyView(R.layout.holder_empty, (ViewGroup) rvTweets.getParent());
        if(getArguments() != null && getArguments().containsKey(Constants.ARG_SEARCH_WORD)) {
            svSearch.setQuery(getArguments().getString(Constants.ARG_SEARCH_WORD), false);
        }
    }


    @Override
    public void setNewData(List<TweetDH> tweets) {
        mTweetAdapter.setNewData(tweets);
    }

    @Override
    public void addData(List<TweetDH> tweets) {
        mTweetAdapter.addData(tweets);
        mTweetAdapter.loadMoreComplete();
    }

    @Override
    public void noMoreData() {
        mTweetAdapter.loadMoreEnd();
    }

    @Override
    public void favoriteAdded() {
        showMessage(getResources().getString(R.string.message_favorite_added));
    }

    @Override
    public void favoriteRemoved() {
        showMessage(getResources().getString(R.string.message_favorite_removed));
    }

    @Override
    public void stopRefreshing() {
        srlTweets.setRefreshing(false);
    }

    @Override
    public void handleFavorite(TweetDH tweetDH, boolean add, int position) {
        presenter.addFavorite(tweetDH, add);
    }

    @Override
    public void handleEntityClick(AutoLinkMode autoLinkMode, String matchedText) {
        svSearch.setQuery(matchedText, false);
    }
}
