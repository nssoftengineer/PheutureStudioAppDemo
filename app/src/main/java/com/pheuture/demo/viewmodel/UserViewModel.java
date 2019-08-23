package com.pheuture.demo.viewmodel;


import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.BindingAdapter;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.pheuture.demo.BuildConfig;
import com.pheuture.demo.view.activity.MainActivity;
import com.pheuture.demo.view.activity.TabActivity;
import com.pheuture.demo.api.RestApi;
import com.pheuture.demo.model.User;
import com.pheuture.demo.utils.Const;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by neeraj on 9/20/2018.
 */


public class UserViewModel extends Observable {

    private User user;
    private List<User> userList;
    private Context context;
    private Uri picUri;

    public UserViewModel(Context context) {
        userList = new ArrayList<>();
        this.context = context;
    }

    public Uri getPicUri() {
        return picUri;
    }

    public void setPicUri(Uri picUri) {
        this.picUri = picUri;
    }

    public UserViewModel(User user) {
        this.user = user;
    }

    public List<User> getUserList() {
        return userList;
    }

    public String getFullName() {
        return user.getFullName();
    }

    public String getMobile() {
        return user.getUserMobnum();
    }

    public String getImageUrl() {
        return user.getProfileImg();
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.get()
                .load(imageUrl)
                .into(view);
    }

    //call activity and save data
    public void onItemClick(View view) {
        view.getContext().startActivity(TabActivity.launchDetail(view.getContext(), user.getFullName(), user.getUserMobnum(), 1));
    }

    //call activity and save data
    public void onClick(View view) {
        view.getContext().startActivity(TabActivity.launchDetail(view.getContext(), 0));
    }

    //profile image click
    public void onImageClick(View view) {

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_DENIED && ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_DENIED) {
            try {

                File f = new File(Environment.getExternalStorageDirectory(), "temp.jpg");
                Uri photoURI = FileProvider.getUriForFile(context,
                        BuildConfig.APPLICATION_ID + ".provider",
                        f);
                setPicUri(photoURI);
                Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, getPicUri());
                if (captureIntent.resolveActivity(context.getPackageManager()) != null) {
                    ((Activity) context).startActivityForResult(captureIntent, Const.CAMERA_CAPTURE);
                }

            } catch (ActivityNotFoundException anfe) {
                Toast toast = Toast.makeText(context, "This device doesn't support the crop action!",
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        } else {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

    }

    //bind data from rest api using retrofit
    public void dataBind(final ProgressBar progressBar) {

        progressBar.setVisibility(View.VISIBLE);

        RestApi.getApiInterface().getUser().enqueue(new Callback<User>() {


            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                List<User> users = (List<User>) response.body().getData();
                notifiedDataChanges(users);
                if (MainActivity.userDatabase.myDao().getAllUser().size() == 0) {
                    for (User user : users) {
                        MainActivity.userDatabase.myDao().addUser(user);
                    }

                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    //update data and notify observer
    private void notifiedDataChanges(List<User> users) {
        getUserList().clear();
        getUserList().addAll(users);
        setChanged();
        notifyObservers();
    }


    //crop activity open
    public void performCrop() {

        CropImage.activity(getPicUri())
                .start((Activity) context);

    }
}
