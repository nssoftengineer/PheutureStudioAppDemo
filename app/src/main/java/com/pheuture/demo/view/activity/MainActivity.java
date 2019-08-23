package com.pheuture.demo.view.activity;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.pheuture.demo.R;
import com.pheuture.demo.view.adapter.UserAdapter;
import com.pheuture.demo.database.UserDatabase;
import com.pheuture.demo.databinding.ActivityMainBinding;
import com.pheuture.demo.utils.Const;
import com.pheuture.demo.viewmodel.UserViewModel;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by neeraj on 9/20/2018.
 */


public class MainActivity extends AppCompatActivity implements Observer {

    public static UserDatabase userDatabase;
    ActivityMainBinding activityMainBinding;
    UserViewModel userViewModel;
    UserAdapter userAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getUserDatabase();
        binding();
        bindAdapter();
        setupObserver(userViewModel);
        userViewModel.dataBind(activityMainBinding.progressBar);

    }

    private void getUserDatabase() {
        userDatabase= Room.databaseBuilder(this,UserDatabase.class,"user_db").allowMainThreadQueries().build();
    }

    private void binding() {
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        userViewModel = new UserViewModel(this);
        activityMainBinding.setUserViewModel(userViewModel);
        getSupportActionBar().hide();



        activityMainBinding.pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               userViewModel.dataBind(activityMainBinding.progressBar);
                activityMainBinding.pullToRefresh.setRefreshing(false);
            }
        });
    }

    public void setupObserver(Observable observable) {
        observable.addObserver((Observer) this);
    }

    private void bindAdapter() {
        userAdapter = new UserAdapter(this);
        activityMainBinding.recyclerMain.setLayoutManager(new LinearLayoutManager(this));
        activityMainBinding.recyclerMain.setAdapter(userAdapter);
    }


    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof UserViewModel) {
            userAdapter.setData(((UserViewModel) o).getUserList());
        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == Const.CAMERA_CAPTURE) {
                userViewModel.performCrop();
            }
            // user is returning from cropping the image
            else  if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                Uri resultUri = result.getUri();
                activityMainBinding.imageViewLogo.setImageURI(resultUri);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1)
        {
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
               userViewModel.onImageClick(null);
            }
        }
    }


}
