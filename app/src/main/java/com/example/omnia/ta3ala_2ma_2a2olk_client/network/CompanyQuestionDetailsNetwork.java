package com.example.omnia.ta3ala_2ma_2a2olk_client.network;

import android.util.Log;

import com.example.omnia.ta3ala_2ma_2a2olk_client.Interfaces.ComapnyQuestionListInterface;
import com.example.omnia.ta3ala_2ma_2a2olk_client.Interfaces.CompanyQuestionDetailsInterface;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Answer;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.CompanyQuestionForTitle;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.CompanyQuestionForTitleList;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Question;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Report;
import com.example.omnia.ta3ala_2ma_2a2olk_client.rest.APIService;
import com.example.omnia.ta3ala_2ma_2a2olk_client.rest.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by omnia on 6/1/2018.
 */

public class CompanyQuestionDetailsNetwork {

    CompanyQuestionDetailsInterface cListPresener;

    public CompanyQuestionDetailsNetwork(CompanyQuestionDetailsInterface cql) {
        this.cListPresener = cql;
    }

    public void getQuestionDetailsNetwork(String Qid, String token) {

        APIService apiService =
                ApiClient.getApiClient().create(APIService.class);
        Call<Question> call = apiService.getCompaniesQuestionsDetails(Qid, token);
        call.enqueue(new Callback<Question>() {
            @Override
            public void onResponse(Call<Question> call, Response<Question> response) {

                Question q = response.body();
                List<Answer> s = response.body().getAnswersCollection();
                cListPresener.setCompanyQuetionDetailsPresenter(q, s);

            }

            @Override
            public void onFailure(Call<Question> call, Throwable t) {
            }
        });
    }

    public void deleteQuestionNetwork(Question q, String token) {

        APIService apiService =
                ApiClient.getApiClient().create(APIService.class);
        Call<String> call = apiService.deleteQuestion(q, token);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                // response.body();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }

    public void editQuestionNetwork(Question question, String token) {

        APIService apiService =
                ApiClient.getApiClient().create(APIService.class);
        Call<String> call = apiService.editQuestion(question, token);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                // response.body();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }

    public void questionUpRateNetwork(String question_id, String token) {

        APIService apiService =
                ApiClient.getApiClient().create(APIService.class);
        Call<String> call = apiService.questionUpRate(question_id, token);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                Log.i("dd", "onResponse: ");
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }

    public void questionDownRateNetwork(String question_id, String token){

        APIService apiService =
                ApiClient.getApiClient().create(APIService.class);
        Call<String> call = apiService.questionDownRate(question_id, token);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                Log.i("dd", "onResponse: ");
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }

    public void reportQuestionNetwork(Report report,String token){
        APIService apiService =
                ApiClient.getApiClient().create(APIService.class);
        Call<String> call = apiService.reportQuestion(report, token);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                Log.i("ll", "onResponse: ");
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }
}
