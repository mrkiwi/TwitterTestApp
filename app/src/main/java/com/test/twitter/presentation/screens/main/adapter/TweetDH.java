package com.test.twitter.presentation.screens.main.adapter;

import com.test.twitter.data.local.model.TweetModel;
import com.test.twitter.data.model.common.StatusItem;

public class TweetDH {
    public long id;
    public String authorName;
    public String avatar;
    public String tags;
    public String mentions;
    public String text;
    public boolean isFavorite = false;

    public TweetDH(StatusItem item) {
        this.authorName = item.user.screenName;
        this.text = item.text;
        this.avatar = item.user.profileImageUrl;
        this.tags = item.entities.getHashTags();
        this.mentions = item.entities.getMentions();
        this.id = item.id;
    }

    public TweetDH(TweetModel item) {
        this.authorName = item.getAuthorName();
        this.text = item.getText();
        this.avatar = item.getAvatar();
        this.tags = item.getTags();
        this.mentions = item.getMentions();
        this.id = item.getId();
        this.isFavorite = true;
    }


}
