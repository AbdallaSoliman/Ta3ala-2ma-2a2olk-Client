package com.example.omnia.ta3ala_2ma_2a2olk_client.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "https://t3ala2ma2olk.herokuapp.com";
    // public static final String BASE_URL = "http://192.168.137.1:8080/webservice3/app";
    public static Retrofit retrofit = null;

    public static Retrofit getApiClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit;
    }


}
/*
int cacheSize = 10 * 1024 * 1024; // 10 MB
Cache cache = new Cache(getCacheDir(), cacheSize);

OkHttpClient okHttpClient = new OkHttpClient.Builder()
        .cache(cache)
        .build();

Retrofit.Builder builder = new Retrofit.Builder()
        .baseUrl("http://10.0.2.2:3000/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create());

Retrofit retrofit = builder.build();
 */
