package com.example.omnia.ta3ala_2ma_2a2olk_client.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by omnia on 5/22/2018.
 */

public class RetrofitClient {

    //public static final String BASE_URL = "https://gpwebservice.herokuapp.com";

    public static final String BASE_URL = "http://172.16.5.192:8080/WebApplication1/app/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
