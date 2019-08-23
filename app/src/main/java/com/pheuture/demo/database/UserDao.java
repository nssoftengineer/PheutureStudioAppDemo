package com.pheuture.demo.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.pheuture.demo.model.User;

import java.util.List;

/**
 * Created by neeraj on 9/20/2018.
 */


@Dao
public interface UserDao {

    @Insert
    public void addUser(User user);

    @Update
    public void updateUser(User user);

    @Query("select * from user")
    public List<User> getAllUser();


}
