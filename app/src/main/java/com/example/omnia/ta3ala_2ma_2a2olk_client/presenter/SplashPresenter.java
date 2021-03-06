package com.example.omnia.ta3ala_2ma_2a2olk_client.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.omnia.ta3ala_2ma_2a2olk_client.Interfaces.SplashInterface;
import com.example.omnia.ta3ala_2ma_2a2olk_client.SharredPreference.SharredPreferenceManager;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Tocken;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.TockenReturn;
import com.example.omnia.ta3ala_2ma_2a2olk_client.rest.APIService;
import com.example.omnia.ta3ala_2ma_2a2olk_client.rest.ApiClient;
import com.example.omnia.ta3ala_2ma_2a2olk_client.view.LoginActivity;
import com.example.omnia.ta3ala_2ma_2a2olk_client.view.MainActivity;
import com.example.omnia.ta3ala_2ma_2a2olk_client.view.SignUp;
import com.example.omnia.ta3ala_2ma_2a2olk_client.view.SplashScreen;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class SplashPresenter implements SplashInterface.presenter {
    SplashInterface.view view;
    APIService service;

    public SplashPresenter(SplashInterface.view view) {
        this.view = view;
    }


    @Override
    public void loadTockenFromServer(final Context mcontext) {
//        Toast.makeText(mcontext, "Loading Tocken.......", Toast.LENGTH_LONG).show();
        final Tocken tocken1 = new Tocken("user", "user");
        service = ApiClient.getApiClient().create(APIService.class);
        SharedPreferences tokenDetails = mcontext.getSharedPreferences("PersonToken", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = tokenDetails.edit();
        SharedPreferences pref = mcontext.getSharedPreferences("LoginPref", MODE_PRIVATE);
        SharredPreferenceManager manager1 = new SharredPreferenceManager(mcontext);
        SharredPreferenceManager manager = new SharredPreferenceManager(mcontext);
        String token = manager.getString(tokenDetails, "persontoken", "no");
        String id = manager1.getString(pref,"id","no");
        if (id != "no") {
//            Toast.makeText(mcontext, token, Toast.LENGTH_LONG).show();
           view.checksharredpreference();
            view.finishActivity();
        } else {
        Call<TockenReturn> call = service.getTocken("application/json", tocken1);
        call.enqueue(new Callback<TockenReturn>() {
            @Override
            public void onResponse(Call<TockenReturn> call, Response<TockenReturn> response) {
//                Toast.makeText(mcontext, " success ", Toast.LENGTH_LONG).show();
                String tocken = "";
                if (response.body() != null) {
                    tocken = response.body().getTocken();
                    Log.i("tocken", tocken);
                } else {
//                    Toast.makeText(mcontext, "Please Connect To The Internet To Get Last Posts", Toast.LENGTH_LONG).show();
                }
                SharedPreferences pref = mcontext.getSharedPreferences("PersonToken", 0); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();
                SharredPreferenceManager m1 = new SharredPreferenceManager(mcontext);
                String tokenvalue = "Bearer " + tocken;
                m1.setString(pref, "persontoken1", tokenvalue);
                view.checksharredpreference();
                view.finishActivity();
            }

            @Override
            public void onFailure(Call<TockenReturn> call, Throwable t) {
//                Toast.makeText(mcontext, "Splash Presenter On Failer Method", Toast.LENGTH_LONG).show();
            }
        });
    }
     }

}
