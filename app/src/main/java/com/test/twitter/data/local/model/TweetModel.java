package com.test.twitter.data.local.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.test.twitter.presentation.screens.main.adapter.TweetDH;

@Entity(tableName = "tweets")
public class TweetModel {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo(name = "author_name")
    private String authorName;

    @ColumnInfo(name = "avatar")
    private String avatar;

    @ColumnInfo(name = "tags")
    private String tags;

    @ColumnInfo(name = "mentions")
    private String mentions;

    @ColumnInfo(name = "text")
    private String text;


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getMentions() {
        return mentions;
    }

    public void setMentions(String mentions) {
        this.mentions = mentions;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TweetModel() {
    }

    public TweetModel(TweetDH tweetDH) {
        this.id = tweetDH.id;
        this.authorName = tweetDH.authorName;
        this.avatar = tweetDH.avatar;
        this.mentions = tweetDH.mentions;
        this.tags = tweetDH.tags;
        this.text = tweetDH.text;
    }
}
