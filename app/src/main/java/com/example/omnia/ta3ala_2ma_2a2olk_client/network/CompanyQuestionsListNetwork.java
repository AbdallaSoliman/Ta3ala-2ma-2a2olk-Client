package com.example.omnia.ta3ala_2ma_2a2olk_client.network;

import android.util.Log;

import com.example.omnia.ta3ala_2ma_2a2olk_client.Interfaces.ComapnyQuestionListInterface;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.CompanyQuestionForTitle;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.CompanyQuestionForTitleList;
import com.example.omnia.ta3ala_2ma_2a2olk_client.rest.APIService;
import com.example.omnia.ta3ala_2ma_2a2olk_client.rest.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by omnia on 5/31/2018.
 */

public class CompanyQuestionsListNetwork {

    ComapnyQuestionListInterface cListPresener;

    public CompanyQuestionsListNetwork(ComapnyQuestionListInterface cql){
        this.cListPresener=cql;
    }

    public void getCompaniesQuestionNetwork(String Qid,String token){

        APIService apiService =
                ApiClient.getApiClient().create(APIService.class);
        Call<CompanyQuestionForTitleList> call = apiService.getCompaniesQuestionsTitle(Qid, token);
        call.enqueue(new Callback<CompanyQuestionForTitleList>() {
            @Override
            public void onResponse(Call<CompanyQuestionForTitleList> call, Response<CompanyQuestionForTitleList> response) {

                List<CompanyQuestionForTitle> questionsForTitle = response.body().getQuestions();
                cListPresener.setCompanyQuetionListPresenter(questionsForTitle);
              }

            @Override
            public void onFailure(Call<CompanyQuestionForTitleList> call, Throwable t) {
            }
        });
    }


}
