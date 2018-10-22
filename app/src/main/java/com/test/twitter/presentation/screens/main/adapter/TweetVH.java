package com.test.twitter.presentation.screens.main.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.luseen.autolinklibrary.AutoLinkMode;
import com.luseen.autolinklibrary.AutoLinkOnClickListener;
import com.luseen.autolinklibrary.AutoLinkTextView;
import com.test.twitter.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class TweetVH extends BaseViewHolder {

    @BindView(R.id.iv_favorite)
    protected ImageView ivFavorite;
    @BindView(R.id.civ_authorAvatar)
    protected CircleImageView civAuthorAvatar;
    @BindView(R.id.tv_authorName)
    protected TextView tvAuthorName;
    @BindView(R.id.tv_text)
    protected AutoLinkTextView tvText;
    @BindView(R.id.tv_tags)
    protected AutoLinkTextView tvTags;
    @BindView(R.id.tv_mentions)
    protected AutoLinkTextView tvMentions;


    public TweetVH(View view) {
        super(view);
        ButterKnife.bind(this, view);
        tvText.addAutoLinkMode(AutoLinkMode.MODE_HASHTAG,AutoLinkMode.MODE_MENTION, AutoLinkMode.MODE_URL);
        tvTags.addAutoLinkMode(AutoLinkMode.MODE_HASHTAG);
        tvMentions.addAutoLinkMode(AutoLinkMode.MODE_MENTION);
    }

    public void bindData(Context context, TweetDH data) {
        tvAuthorName.setText(data.authorName);
        tvText.setAutoLinkText(data.text);
        tvTags.setAutoLinkText(data.tags);
        tvMentions.setAutoLinkText(data.mentions);
        Glide.with(context)
                .load(data.avatar)
                .into(civAuthorAvatar);

        toggleFavorite(data.isFavorite);
    }

    public void toggleFavorite(boolean isFavorite) {
        ivFavorite.setImageResource(isFavorite ? R.drawable.ic_favorite_active : R.drawable.ic_favorite);
    }

    public void setEntityListener(AutoLinkOnClickListener listener) {
        tvText.setAutoLinkOnClickListener(listener);
        tvTags.setAutoLinkOnClickListener(listener);
        tvMentions.setAutoLinkOnClickListener(listener);
    }

}
