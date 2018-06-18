package com.example.omnia.ta3ala_2ma_2a2olk_client.rest;

import com.example.omnia.ta3ala_2ma_2a2olk_client.model.MainCategories;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by HeshamMuhammed on 6/14/2018.
 */

public interface Check {
    @GET("MainCategories?size=1000")
    Call<List<MainCategories>> mainCategories(@Header("Authorization") String token);
}
