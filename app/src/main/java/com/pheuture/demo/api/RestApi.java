package com.pheuture.demo.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by neeraj on 9/20/2018.
 */


public class RestApi {

    public static final String BASE_URL = "http://pheuture.com/kumar/opinio/";

    public static ApiInterface getApiInterface() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        return retrofit.create(ApiInterface.class);
    }

}
