package com.example.omnia.ta3ala_2ma_2a2olk_client.network;

import android.util.Log;

import com.example.omnia.ta3ala_2ma_2a2olk_client.Interfaces.ComapnyQuestionListInterface;
import com.example.omnia.ta3ala_2ma_2a2olk_client.Interfaces.CompanyQuestionDetailsInterface;
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
//abdalla start
                if(response.body().getAnswersCollection()!=null ) {
                    Question q = response.body();
                    List<Answer> s = response.body().getAnswersCollection();
                    cListPresener.setCompanyQuetionDetailsPresenter(q, s);
                }
//abdalla end
            }

            @Override
            public void onFailure(Call<Question> call, Throwable t) {
            }
        });
    }

    public void deleteQuestionNetwork(Question q, String token) {

        APIService apiService =
                ApiClient.getApiClient().create(APIService.class);
        Call<TockenReturn> call = apiService.deleteQuestion(q, token);
        call.enqueue(new Callback<TockenReturn>() {
            @Override
            public void onResponse(Call<TockenReturn> call, Response<TockenReturn> response) {

                // response.body();
            }

            @Override
            public void onFailure(Call<TockenReturn> call, Throwable t) {
            }
        });
    }

    public void editQuestionNetwork(Question question, String token) {

        APIService apiService =
                ApiClient.getApiClient().create(APIService.class);
        Call<TockenReturn> call = apiService.editQuestion(question, token);
        call.enqueue(new Callback<TockenReturn>() {
            @Override
            public void onResponse(Call<TockenReturn> call, Response<TockenReturn> response) {

                // response.body();
            }

            @Override
            public void onFailure(Call<TockenReturn> call, Throwable t) {
            }
        });
    }

    public void questionUpRateNetwork(String question_id, String token) {

        Log.i("tokken", token);
        Log.i("qq", question_id);
        APIService apiService =
                ApiClient.getApiClient().create(APIService.class);
        Call<TockenReturn> call = apiService.questionUpRate(question_id, token);
        call.enqueue(new Callback<TockenReturn>() {
            @Override
            public void onResponse(Call<TockenReturn> call, Response<TockenReturn> response) {

                Log.i("qdd", "onResponse: ");
            }
            @Override
            public void onFailure(Call<TockenReturn> call, Throwable t) {
                Log.i("qdd", t.getMessage());
            }
        });
    }

    public void questionDownRateNetwork(String question_id, String token){

        APIService apiService =
                ApiClient.getApiClient().create(APIService.class);
        Call<TockenReturn> call = apiService.questionDownRate(question_id, token);
        call.enqueue(new Callback<TockenReturn>() {
            @Override
            public void onResponse(Call<TockenReturn> call, Response<TockenReturn> response) {

                Log.i("dd", "onResponse: ");
            }
            @Override
            public void onFailure(Call<TockenReturn> call, Throwable t) {
            }
        });
    }

    public void reportQuestionNetwork(Report report,String token){
        APIService apiService =
                ApiClient.getApiClient().create(APIService.class);
        Call<TockenReturn> call = apiService.reportQuestion(report, token);
        call.enqueue(new Callback<TockenReturn>() {
            @Override
            public void onResponse(Call<TockenReturn> call, Response<TockenReturn> response) {

                Log.i("ll", "onResponse: ");
            }
            @Override
            public void onFailure(Call<TockenReturn> call, Throwable t) {
            }
        });
    }
}
