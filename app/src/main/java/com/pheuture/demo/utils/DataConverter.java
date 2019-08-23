package com.pheuture.demo.utils;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pheuture.demo.model.User;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by neeraj on 9/20/2018.
 */


public class DataConverter {

    @TypeConverter
    public String fromUsersList(List<User> users) {
        if (users == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<User>>() {
        }.getType();
        String json = gson.toJson(users, type);
        return json;
    }

    @TypeConverter
    public List<User> toUsersList(String userString) {
        if (userString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<User>>() {
        }.getType();
        List<User> userList = gson.fromJson(userString, type);
        return userList;
    }
}