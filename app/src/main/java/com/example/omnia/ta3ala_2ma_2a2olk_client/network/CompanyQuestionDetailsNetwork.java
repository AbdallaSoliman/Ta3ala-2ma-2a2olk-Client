package com.example.omnia.ta3ala_2ma_2a2olk_client.network;

import android.util.Log;

import com.example.omnia.ta3ala_2ma_2a2olk_client.Interfaces.ComapnyQuestionListInterface;
import com.example.omnia.ta3ala_2ma_2a2olk_client.Interfaces.CompanyQuestionDetailsInterface;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.CompanyQuestionForTitle;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.CompanyQuestionForTitleList;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Question;
import com.example.omnia.ta3ala_2ma_2a2olk_client.rest.APIService;
import com.example.omnia.ta3ala_2ma_2a2olk_client.rest.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by omnia on 6/1/2018.
 */

public class CompanyQuestionDetailsNetwork {

    CompanyQuestionDetailsInterface cListPresener;

    public CompanyQuestionDetailsNetwork(CompanyQuestionDetailsInterface cql){
        this.cListPresener=cql;
    }

    public void getQuestionDetailsNetwork(String Qid,String token){

        APIService apiService =
                ApiClient.getApiClient().create(APIService.class);
        Call<Question> call = apiService.getCompaniesQuestionsDetails(Qid, token);
        call.enqueue(new Callback<Question>() {
            @Override
            public void onResponse(Call<Question> call, Response<Question> response) {

                Question q = response.body();
                cListPresener.setCompanyQuetionDetailsPresenter(q);

             }

            @Override
            public void onFailure(Call<Question> call, Throwable t) {
            }
        });
    }
}
