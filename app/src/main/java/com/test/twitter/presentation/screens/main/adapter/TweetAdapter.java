package com.test.twitter.presentation.screens.main.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.luseen.autolinklibrary.AutoLinkMode;
import com.test.twitter.R;

import java.util.List;

public class TweetAdapter extends BaseQuickAdapter<TweetDH, TweetVH> {

    public TweetAdapter(int layoutResId, @Nullable List<TweetDH> data) {
        super(layoutResId, data);
    }
    private TweetClickListener listener;

    public void setListener(TweetClickListener listener) {
        this.listener = listener;
    }


    @Override
    protected void convert(TweetVH helper, TweetDH item) {
        helper.bindData(mContext, item);
        helper.getView(R.id.iv_favorite).setOnClickListener(view -> {
            item.isFavorite = !item.isFavorite;
            helper.toggleFavorite(mContext, item.isFavorite);
            listener.handleFavorite(item, item.isFavorite, helper.getAdapterPosition());
        });
        helper.setEntityListener((AutoLinkMode autoLinkMode, String matchedText) -> {
            listener.handleEntityClick(autoLinkMode, matchedText);
        });
    }

    public interface TweetClickListener {
        void handleFavorite(TweetDH tweetDH, boolean add, int position);
        void handleEntityClick(AutoLinkMode autoLinkMode, String matchedText);
    }
}
