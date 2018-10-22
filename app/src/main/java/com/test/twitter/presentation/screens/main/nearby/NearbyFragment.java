package com.test.twitter.presentation.screens.main.nearby;

import android.content.Context;
import android.location.LocationManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.luseen.autolinklibrary.AutoLinkMode;
import com.test.twitter.R;
import com.test.twitter.presentation.core.base.BaseFragment;
import com.test.twitter.presentation.screens.main.adapter.TweetAdapter;
import com.test.twitter.presentation.screens.main.adapter.TweetDH;
import com.test.twitter.presentation.screens.main.search.SearchFragment;
import com.test.twitter.presentation.utils.DialogBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class NearbyFragment extends BaseFragment<NearbyContract.Presenter> implements NearbyContract.View, TweetAdapter.TweetClickListener  {

    public static NearbyFragment newInstance() {
        return new NearbyFragment();
    }

    @BindView(R.id.rv_tweets)
    RecyclerView rvTweets;
    @BindView(R.id.srl_tweets)
    SwipeRefreshLayout srlTweets;

    private TweetAdapter mTweetAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_nearby;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.subscribe();
        provideLocation();
    }

    @Override
    public void initUI() {
        setTitle(R.string.title_nearby);
        setProgressContentView(getView());
        rvTweets.setLayoutManager(new LinearLayoutManager(getContext()));
        mTweetAdapter = new TweetAdapter(R.layout.item_tweet, new ArrayList<>());
        srlTweets.setOnRefreshListener(() -> {
            presenter.refresh();
        });
        rvTweets.setAdapter(mTweetAdapter);
        mTweetAdapter.setOnLoadMoreListener(() -> presenter.loadMore(), rvTweets);
        mTweetAdapter.setListener(this);
    }

    private void provideLocation() {
        if(checkLocation()) {
            presenter.requestPermission(getActivity());
        }
        else  {
            showTurnOnLocationDialog();
        }
    }

    @Override
    public void showPermissionDeniedMessage() {
        showMessage(getResources().getString(R.string.message_location_permission_denied));
    }

    @Override
    public void setNewData(List<TweetDH> tweets) {
        mTweetAdapter.setNewData(tweets);
        srlTweets.setRefreshing(false);
    }

    @Override
    public void addData(List<TweetDH> tweets) {
        mTweetAdapter.addData(tweets);
        mTweetAdapter.loadMoreComplete();
    }

    private boolean checkLocation() {
        LocationManager lm = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;
        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch(Exception ignored) {}
        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch(Exception ignored) {}

        return gps_enabled && network_enabled;
    }

    @Override
    public void noMoreData() {
        mTweetAdapter.loadMoreEnd();
    }

    private void showTurnOnLocationDialog() {
        DialogBuilder.build(getContext(),
                getResources().getString(R.string.dialog_location_text),
                getResources().getString(R.string.dialog_location_button_retry)
                ,getResources().getString(R.string.dialog_location_button_cancel),
                (dialogInterface, i) -> provideLocation(), false);
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
    public void handleFavorite(TweetDH tweetDH, boolean add, int position) {
        presenter.addFavorite(tweetDH, add);
    }

    @Override
    public void handleEntityClick(AutoLinkMode autoLinkMode, String matchedText) {
        getNavigator().switchFragment(SearchFragment.newInstance(matchedText), false);
    }
}
