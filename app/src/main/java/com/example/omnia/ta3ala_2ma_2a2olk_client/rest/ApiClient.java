package com.example.omnia.ta3ala_2ma_2a2olk_client.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "https://gpwebservice.herokuapp.com";
    public static Retrofit retrofit = null ;

    public static Retrofit getApiClient() {

          if (retrofit == null){
              retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
          }

            return retrofit;
    }



}
