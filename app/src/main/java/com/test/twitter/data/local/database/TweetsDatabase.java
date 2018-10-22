package com.test.twitter.data.local.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.test.twitter.data.local.dao.TweetsDao;
import com.test.twitter.data.local.model.TweetModel;

@Database(entities = {TweetModel.class}, version = 1)
public abstract class TweetsDatabase extends RoomDatabase {

    static final int VERSION = 1;

    public abstract TweetsDao getTweetsDao();

}
