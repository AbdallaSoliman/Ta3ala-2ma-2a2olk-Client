package com.example.omnia.ta3ala_2ma_2a2olk_client.rest;

/**
 * Created by omnia on 5/22/2018.
 */

import com.example.omnia.ta3ala_2ma_2a2olk_client.model.MainCat;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.MainCategory;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Question;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Tocken;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.TockenReturn;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.User;

import java.util.List;

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

//    @GET("/getPerson")
//    Call<User> getUser(@Query("email") String email, @Query("password") String password);

//    @POST("/getPerson")
//    Call<User> registerUser(@Body User login);

    @POST("/auth")
    Call<TockenReturn> getTocken(@Header("Content-Type") String content_type, @Body Tocken tocken);

    @POST("/Person")
    Call<User> registerUser(@Header("Content-Type") String content_type, @Header("Authorization") String token, @Body User tocken);

    @POST("/auth")
    Call<TockenReturn> loginUser(@Header("Content-Type") String content_type, @Header("Authorization") String token, @Body Tocken user);
    @POST("/Person/Login")
    Call<User> loginUserWithMail(@Header("Content-Type") String content_type, @Header("Authorization") String token, @Body User user);
    @GET("/MainCategoryWithSubs")
    Call<List<MainCat>> getCategories(@Header("Content-Type") String content_type, @Header("Authorization") String token);



}
