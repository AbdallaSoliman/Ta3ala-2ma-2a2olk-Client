package com.example.omnia.ta3ala_2ma_2a2olk_client.network;

import android.util.Log;

import com.example.omnia.ta3ala_2ma_2a2olk_client.Interfaces.Tab2CustomerServiceInterface;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.MainCategory;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.SubCategory;
import com.example.omnia.ta3ala_2ma_2a2olk_client.presenter.Tab2CustomerServicePresenter;
import com.example.omnia.ta3ala_2ma_2a2olk_client.rest.APIService;
import com.example.omnia.ta3ala_2ma_2a2olk_client.rest.ApiClient;
import com.example.omnia.ta3ala_2ma_2a2olk_client.view.Tab2CustomerService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by omnia on 5/30/2018.
 */

public class Tab2CustomerServiceNetwork {

    List<SubCategory> companies=new ArrayList<>();
    Tab2CustomerServiceInterface presenterREFR;

    public Tab2CustomerServiceNetwork(Tab2CustomerServiceInterface presenterREFR) {
        this.presenterREFR = presenterREFR;
    }

    public void getCompaniesFromNetwork(String token){

        APIService apiService =
                ApiClient.getApiClient().create(APIService.class);
        Call<MainCategory> call = apiService.getCompanies("application/json", token);
        call.enqueue(new Callback<MainCategory>() {
            @Override
            public void onResponse(Call<MainCategory> call, Response<MainCategory> response) {

                companies.addAll(response.body().getSubCatCollection());
                presenterREFR.setCompaniesList(companies);
               }

            @Override
            public void onFailure(Call<MainCategory> call, Throwable t) {
            }
        });
    }


}
