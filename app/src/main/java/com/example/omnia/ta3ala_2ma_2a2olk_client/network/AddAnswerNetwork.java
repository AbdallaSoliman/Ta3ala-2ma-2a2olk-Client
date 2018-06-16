package com.example.omnia.ta3ala_2ma_2a2olk_client.network;

import android.util.Log;

import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Answer;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.TockenReturn;
import com.example.omnia.ta3ala_2ma_2a2olk_client.presenter.AddAnswerPresenter;
import com.example.omnia.ta3ala_2ma_2a2olk_client.rest.APIService;
import com.example.omnia.ta3ala_2ma_2a2olk_client.rest.ApiClient;

import okhttp3.CacheControl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by omnia on 6/6/2018.
 */

public class AddAnswerNetwork {

    public void saveAnswerToServerNetwork(Answer answer, String token){

        APIService apiService =
                ApiClient.getApiClient().create(APIService.class);
        Call<TockenReturn> call = apiService.saveAnswer(answer, token);
//        abdalla start
        call.request().newBuilder().cacheControl(CacheControl.FORCE_NETWORK);
//        abdalla end
        call.enqueue(new Callback<TockenReturn>() {
            @Override
            public void onResponse(Call<TockenReturn> call, Response<TockenReturn> response) {

//                if(response.body().equals("Add Done successfully")){
//                    Log.i("hh", "onResponse: ");
//                }
            }
            @Override
            public void onFailure(Call<TockenReturn> call, Throwable t) {
            }
        });
    }
}
