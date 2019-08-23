package com.pheuture.demo.api;

import com.pheuture.demo.model.User;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by neeraj on 9/20/2018.
 */

public interface ApiInterface {
    @GET("demo_api.php")
    Call<User> getUser();
}
