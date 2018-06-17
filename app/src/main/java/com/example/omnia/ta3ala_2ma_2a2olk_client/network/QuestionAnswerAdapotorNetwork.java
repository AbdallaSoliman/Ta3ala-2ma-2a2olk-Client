package com.example.omnia.ta3ala_2ma_2a2olk_client.network;

import android.util.Log;

import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Answer;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.CompanyQuestionForTitle;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.CompanyQuestionForTitleList;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Question;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Report;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.TockenReturn;
import com.example.omnia.ta3ala_2ma_2a2olk_client.rest.APIService;
import com.example.omnia.ta3ala_2ma_2a2olk_client.rest.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by omnia on 6/4/2018.
 */

public class QuestionAnswerAdapotorNetwork {

    public void deleteAnswerNetwork(int answerId,String token){

        String Aid=answerId+"";

        APIService apiService =
                ApiClient.getApiClient().create(APIService.class);
        Call<TockenReturn> call = apiService.deleteAnswer(Aid, token);
        call.enqueue(new Callback<TockenReturn>() {
            @Override
            public void onResponse(Call<TockenReturn> call, Response<TockenReturn> response) {
                if(response.body()!=null) {
                }
            }

            @Override
            public void onFailure(Call<TockenReturn> call, Throwable t) {
            }
        });
    }

    public void editAnswerNetwork(Answer answer, String token){

        APIService apiService =
                ApiClient.getApiClient().create(APIService.class);
        Call<TockenReturn> call = apiService.editAnswer(answer, token);
        call.enqueue(new Callback<TockenReturn>() {
            @Override
            public void onResponse(Call<TockenReturn> call, Response<TockenReturn> response) {
                if(response.body()!=null) {
                }
            }

            @Override
            public void onFailure(Call<TockenReturn> call, Throwable t) {
            }
        });
    }

    public void reportAnswerNetwork(Report report, String token){

        APIService apiService =
                ApiClient.getApiClient().create(APIService.class);
        Call<TockenReturn> call = apiService.reportAnswer(report, token);
        call.enqueue(new Callback<TockenReturn>() {
            @Override
            public void onResponse(Call<TockenReturn> call, Response<TockenReturn> response) {
                if(response.body()!=null) {
                }
            }
            @Override
            public void onFailure(Call<TockenReturn> call, Throwable t) {
            }
        });
    }

    public void answerRateUpNetwork(String answerId,String token){

        APIService apiService =
                ApiClient.getApiClient().create(APIService.class);
        Call<TockenReturn> call = apiService.answerUpRate(answerId, token);
        call.enqueue(new Callback<TockenReturn>() {
            @Override
            public void onResponse(Call<TockenReturn> call, Response<TockenReturn> response) {
                if(response.body()!=null) {
                }
            }
            @Override
            public void onFailure(Call<TockenReturn> call, Throwable t) {
            }
        });

    }
    public void answerRateDownNetwork(String answerId,String token){

        APIService apiService =
                ApiClient.getApiClient().create(APIService.class);
        Call<TockenReturn> call = apiService.answerDownRate(answerId, token);
        call.enqueue(new Callback<TockenReturn>() {
            @Override
            public void onResponse(Call<TockenReturn> call, Response<TockenReturn> response) {
                if(response.body()!=null) {
                }
            }
            @Override
            public void onFailure(Call<TockenReturn> call, Throwable t) {
            }
        });
    }

    public void verifyAnswerNetwork(Question question, String token){

        APIService apiService =
                ApiClient.getApiClient().create(APIService.class);
        Call<TockenReturn> call = apiService.verifyAnswer(question, token);
        call.enqueue(new Callback<TockenReturn>() {
            @Override
            public void onResponse(Call<TockenReturn> call, Response<TockenReturn> response) {
                if(response.body()!=null) {
                }
            }
            @Override
            public void onFailure(Call<TockenReturn> call, Throwable t) {
            }
        });
    }
}
