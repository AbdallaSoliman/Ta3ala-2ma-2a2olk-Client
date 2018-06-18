package com.example.omnia.ta3ala_2ma_2a2olk_client.rest;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.omnia.ta3ala_2ma_2a2olk_client.SharredPreference.SharredPreferenceManager;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.MainCategories;
import com.example.omnia.ta3ala_2ma_2a2olk_client.view.CompanyQuestionsList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by HeshamMuhammed on 6/14/2018.
 */

public class InternetCheck{

//    Check apiCheck;
//    Call<List<MainCategories>> mainCategories;
//    boolean isTrue;
//    Visablity.view companyQuestionsList;
//    public InternetCheck(Visablity.view companyQuestionsList){
//        this.companyQuestionsList = companyQuestionsList;
//    }
//    public boolean getData() {
//        apiCheck = ApiCheck.getApiClient().create(Check.class);
//        companyQuestionsList.downButton();
//        mainCategories = apiCheck.mainCategories(getToken());
//        mainCategories.enqueue(new Callback<List<MainCategories>>() {
//            @Override
//            public void onResponse(Call<List<MainCategories>> call, Response<List<MainCategories>> response) {
//                Toast.makeText(MyApplication.getContext(), "YES", Toast.LENGTH_LONG).show();
//                isTrue = true;
//            //    companyQuestionsList.showButton();
//            }
//
//            @Override
//            public void onFailure(Call<List<MainCategories>> call, Throwable t) {
//                Toast.makeText(MyApplication.getContext(), "NO", Toast.LENGTH_LONG).show();
//                isTrue = false;
//              //  companyQuestionsList.downButton();
//            }
//        });
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//
//        return isTrue;
//    }
//
//    public String getToken() {
//        SharredPreferenceManager shM;
//        SharedPreferences pref = MyApplication.getContext().getSharedPreferences("PersonToken", Context.MODE_PRIVATE);
//        shM = new SharredPreferenceManager(MyApplication.getContext());
//        final String token = shM.getString(pref, "persontoken", "error");
//        return token;
//    }
}
