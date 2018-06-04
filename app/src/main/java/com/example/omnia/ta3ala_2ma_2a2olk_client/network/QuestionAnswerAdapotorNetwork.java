package com.example.omnia.ta3ala_2ma_2a2olk_client.network;

import android.util.Log;

import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Answer;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.CompanyQuestionForTitle;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.CompanyQuestionForTitleList;
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
        Call<String> call = apiService.deleteAnswer(Aid, token);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.body()!=null) {
                   Log.i("oo", response.body());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }

    public void editAnswerNetwork(Answer answer, String token){

        APIService apiService =
                ApiClient.getApiClient().create(APIService.class);
        Call<String> call = apiService.editAnswer(answer, token);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.body()!=null) {
                    Log.i("nn", response.body());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }
}
