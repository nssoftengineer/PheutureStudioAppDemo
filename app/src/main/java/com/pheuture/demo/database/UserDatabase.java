package com.pheuture.demo.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.databinding.adapters.Converters;

import com.pheuture.demo.model.User;

/**
 * Created by neeraj on 9/20/2018.
 */


@Database(entities = User.class, version = 1)
public abstract class UserDatabase extends RoomDatabase {

    public abstract UserDao myDao();
}
