package com.pheuture.demo.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.pheuture.demo.utils.DataConverter;

/**
 * Created by neeraj on 9/20/2018.
 */


@Entity(tableName = "user")
public class User {


    @PrimaryKey(autoGenerate = true)
    private int id;

    @SerializedName("data")
    @TypeConverters(DataConverter.class)
    private List<User> data = new ArrayList<>();

    @SerializedName("full_name")

    private String fullName;
    @SerializedName("profile_img")

    private String profileImg;
    @SerializedName("user_mobnum")

    private String userMobnum;
    @SerializedName("viewType")

    private String viewType;

    @SerializedName("message")
    private Boolean message;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getUserMobnum() {
        return userMobnum;
    }

    public void setUserMobnum(String userMobnum) {
        this.userMobnum = userMobnum;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }



    public Boolean getMessage() {
        return message;
    }

    public void setMessage(Boolean message) {
        this.message = message;
    }


    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }


}
