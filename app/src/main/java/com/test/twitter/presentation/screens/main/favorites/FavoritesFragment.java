package com.test.twitter.presentation.screens.main.favorites;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.luseen.autolinklibrary.AutoLinkMode;
import com.test.twitter.R;
import com.test.twitter.presentation.core.base.BaseFragment;
import com.test.twitter.presentation.screens.main.adapter.TweetAdapter;
import com.test.twitter.presentation.screens.main.adapter.TweetDH;
import com.test.twitter.presentation.screens.main.search.SearchFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FavoritesFragment extends BaseFragment<FavoritesContract.Presenter> implements FavoritesContract.View, TweetAdapter.TweetClickListener  {

    public static FavoritesFragment newInstance() {
        return new FavoritesFragment();
    }

    @BindView(R.id.rv_tweets)
    RecyclerView rvTweets;
    @BindView(R.id.srl_tweets)
    SwipeRefreshLayout srlTweets;

    private TweetAdapter mTweetAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_favorites;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.subscribe();
    }

    @Override
    public void initUI() {
        setTitle(R.string.title_favorites);
        rvTweets.setLayoutManager(new LinearLayoutManager(getContext()));
        mTweetAdapter = new TweetAdapter(R.layout.item_tweet, new ArrayList<>());
        srlTweets.setOnRefreshListener(() -> {
            presenter.refresh();
        });
        rvTweets.setAdapter(mTweetAdapter);
        mTweetAdapter.setListener(this);
    }

    @Override
    public void setNewData(List<TweetDH> tweets) {
        mTweetAdapter.setNewData(tweets);
    }

    @Override
    public void favoriteRemoved() {
        showMessage(getResources().getString(R.string.message_favorite_removed));
    }

    @Override
    public void handleFavorite(TweetDH tweetDH, boolean add, int position) {
        presenter.removeFavorite(tweetDH);
        mTweetAdapter.remove(position);
        mTweetAdapter.notifyItemRemoved(position);
        mTweetAdapter.notifyItemRangeChanged(position, mTweetAdapter.getItemCount());
    }

    @Override
    public void handleEntityClick(AutoLinkMode autoLinkMode, String matchedText) {
        getNavigator().switchFragment(SearchFragment.newInstance(matchedText), false);
    }
}
