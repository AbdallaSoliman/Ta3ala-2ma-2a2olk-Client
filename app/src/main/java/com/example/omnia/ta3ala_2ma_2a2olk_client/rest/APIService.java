package com.example.omnia.ta3ala_2ma_2a2olk_client.rest;

/**
 * Created by omnia on 5/22/2018.
 */

import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Answer;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.CompanyQuestionForTitleList;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.MainCategories;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.MainCategory;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.MainCategorySpecial;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.NewsFeed;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Question;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Report;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Search;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.ServerResonse;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.ServerResponseId;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Tauser;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Tocken;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.TockenReturn;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.User;
import com.google.firebase.auth.GetTokenResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

    @GET("/MainCategories/3")
    Call<MainCategory> getCompanies(@Header("Content-Type") String content_type, @Header("Authorization") String token);

    @GET("/QuestionWithSubCat/{question_id}")
    Call<CompanyQuestionForTitleList> getCompaniesQuestionsTitle(@Path(value = "question_id", encoded = true) String questionId, @Header("Authorization") String token);

    @GET("/Question/{question_id}")
    Call<Question> getCompaniesQuestionsDetails(@Path(value = "question_id", encoded = true) String questionId, @Header("Authorization") String token);

    @Headers({"Content-Type: application/json"})
    @DELETE("/Answers/{answer_id}")
    Call<TockenReturn> deleteAnswer(@Path(value = "answer_id", encoded = true) String answerId, @Header("Authorization") String token);

    @Headers({"Content-Type: application/json"})
    @PUT("/Answers")
    Call<TockenReturn> editAnswer(@Body Answer answer, @Header("Authorization") String token);

    @Headers({"Content-Type: application/json"})
    @PUT("/Question")
    Call<TockenReturn> deleteQuestion(@Body Question q, @Header("Authorization") String token);

    @Headers({"Content-Type: application/json"})
    @PUT("/Question")
    Call<TockenReturn> editQuestion(@Body Question question, @Header("Authorization") String token);

    @Headers({"Content-Type: application/json"})
    @POST("/Answers")
    Call<TockenReturn> saveAnswer(@Body Answer answer, @Header("Authorization") String token);

    @Headers({"Content-Type: application/json"})
    @GET("/QuestionRateUp/{question_id}")
    Call<TockenReturn> questionUpRate(@Path(value = "question_id", encoded = true) String questionId, @Header("Authorization") String token);

    @Headers({"Content-Type: application/json"})
    @GET("/QuestionRateDown/{question_id}")
    Call<TockenReturn> questionDownRate(@Path(value = "question_id", encoded = true) String questionId, @Header("Authorization") String token);

    @Headers({"Content-Type: application/json"})
    @POST("/Report")
    Call<TockenReturn> reportQuestion(@Body Report report, @Header("Authorization") String token);

    @Headers({"Content-Type: application/json"})
    @POST("/Report")
    Call<TockenReturn> reportAnswer(@Body Report report, @Header("Authorization") String token);

    @Headers({"Content-Type: application/json"})
    @GET("/AnswerRateUp/{answer_id}")
    Call<TockenReturn> answerUpRate(@Path(value = "answer_id", encoded = true) String answerId, @Header("Authorization") String token);

    @Headers({"Content-Type: application/json"})
    @GET("/AnswerRateDown/{answer_id}")
    Call<TockenReturn> answerDownRate(@Path(value = "answer_id", encoded = true) String answerId, @Header("Authorization") String token);


    // ahmed hesham
    @GET("MainCategories?size=1000")
    Call<List<MainCategories>> mainCategories(@Header("Authorization") String token);

    @PUT("/Person")
    Call<ServerResonse> updateUser(@Header("Content-Type") String content_type, @Header("Authorization") String token, @Body User user);

    @POST("/TaaUser")
    Call<ServerResonse> addTaUser(@Header("Content-Type") String content_type, @Header("Authorization") String token, @Body User user);

    @POST("/TaaUser")
    Call<ServerResonse> addTaUserdata(@Header("Content-Type") String content_type, @Header("Authorization") String token, @Body Tauser user);

    @GET("MainCategoriesSpecial?size=1000")
    Call<List<MainCategorySpecial>> mainCategoriesSpecial(@Header("Authorization") String token);

    @GET("Question?size=10")
    Call<List<Question>> getQuestion(@Header("Authorization") String token);

    @GET("QuestionNewsFeed?size=10")
    Call<List<NewsFeed>> getNewsFeeds(@Header("Authorization") String token);
    @GET("QuestionFilterCustomization?size=10&page=0")
    Call<List<NewsFeed>> search(@Header("Authorization") String token , @Query("search")String query);


    // hesham muhammed

//    @POST("Question")
//    Call<Question> addQuestion(@Body Question question, @Header("Authorization") String token);
    @POST("Question")
    Call<ServerResponseId> addQuestion(@Header("Content-Type") String content_type,@Body Question question, @Header("Authorization") String token);
}

