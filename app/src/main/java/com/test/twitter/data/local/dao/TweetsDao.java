package com.test.twitter.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.test.twitter.data.local.model.TweetModel;

import java.util.List;

@Dao
public interface TweetsDao {

    @Query("SELECT * FROM tweets")
    List<TweetModel> getAll();


    @Insert
    void insertAll(TweetModel... tweets);

    @Delete
    void delete(TweetModel tweet);

    @Query("DELETE FROM tweets WHERE id = :id")
    void deleteById(long id);
}
