package com.test.twitter.di.modules;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.test.twitter.data.local.dao.TweetsDao;
import com.test.twitter.data.local.database.TweetsDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Singleton
@Module
public class DatabaseModule {

    @Provides
    public TweetsDatabase provideAppDatabase(final Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), TweetsDatabase.class, "db_tweets")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    public TweetsDao tweetsDao(TweetsDatabase tweetsDatabase) {
        return tweetsDatabase.getTweetsDao();
    }
}
