package com.example.omnia.ta3ala_2ma_2a2olk_client.rest;


import com.example.omnia.ta3ala_2ma_2a2olk_client.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {


    @GET("/getPerson")
    Call<User> getUser(@Query("email") String email, @Query("password") String password);

    @POST("/getPerson")
    Call<User> registerUser(@Body User login);


}
