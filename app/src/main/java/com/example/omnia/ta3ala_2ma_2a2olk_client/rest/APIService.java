package com.example.omnia.ta3ala_2ma_2a2olk_client.rest;

/**
 * Created by omnia on 5/22/2018.
 */

import com.example.omnia.ta3ala_2ma_2a2olk_client.model.MainCategory;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Question;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {


    @Headers({"Content-Type: application/json"})
    @POST("/getQuestion")
    Call<Question> saveQuestion(@Body Question question);

    @GET("places")
    Call<MainCategory> getCategories();

}
