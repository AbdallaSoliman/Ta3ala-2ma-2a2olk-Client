package com.example.omnia.ta3ala_2ma_2a2olk_client.rest;

/**
 * Created by omnia on 5/22/2018.
 */

import com.example.omnia.ta3ala_2ma_2a2olk_client.model.MainCategory;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Question;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Tocken;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.TockenReturn;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {

    @Headers({"Content-Type: application/json"})
    @POST("/getQuestion")
    Call<Question> saveQuestion(@Body Question question);

    @GET("places")
    Call<MainCategory> getCategories();

    @POST("/auth")
    Call<TockenReturn> getTocken(@Header("Content-Type") String content_type, @Body Tocken tocken);

    @POST("/Person")
    Call<User> registerUser(@Header("Content-Type") String content_type, @Header("Authorization") String token, @Body User tocken);

    @POST("/auth")
    Call<TockenReturn> loginUser(@Header("Content-Type") String content_type, @Header("Authorization") String token, @Body Tocken user);
    @POST("/Person/Login")
    Call<User> loginUserWithMail(@Header("Content-Type") String content_type, @Header("Authorization") String token, @Body User user);

    //@Headers({"Content-Type: application/json"})
    @GET("/MainCategories/3")
    Call<MainCategory> getCompanies(@Header("Content-Type") String content_type , @Header("Authorization") String token);


}
