package com.example.omnia.ta3ala_2ma_2a2olk_client.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashPresenter implements SplashInterface.presenter {
    SplashInterface.view view ;
    APIService service ;

    public SplashPresenter(SplashInterface.view view){
        this.view = view;
    }


    @Override
    public void loadTockenFromServer(final Context mcontext) {
        Toast.makeText(mcontext, "Loading Tocken.......", Toast.LENGTH_LONG).show();
       final Tocken tocken1 = new Tocken("user" , "user");
        service = ApiClient.getApiClient().create(APIService.class);

        Call<TockenReturn> call = service.getTocken("application/json",tocken1);
        call.enqueue(new Callback<TockenReturn>() {
          @Override
          public void onResponse(Call<TockenReturn> call, Response<TockenReturn> response) {
              Toast.makeText(mcontext, "in success method", Toast.LENGTH_LONG).show();
              if (response.body()!=null) {
                  String tocken = response.body().getTocken();
                  Log.i("tocken", tocken);
                  Toast.makeText(mcontext, tocken, Toast.LENGTH_LONG).show();
                  SharedPreferences pref = mcontext.getSharedPreferences("PersonToken", 0); // 0 - for private mode
                  SharedPreferences.Editor editor = pref.edit();
                  SharredPreferenceManager m1 = new SharredPreferenceManager(mcontext);
                  String tokenvalue = "Bearer "+tocken;
                  m1.setString(pref,"persontoken",tokenvalue);
                  Toast.makeText(mcontext,tokenvalue,Toast.LENGTH_LONG).show();
                  view.checksharredpreference();
//                  Intent intent = new Intent(mcontext, LoginActivity.class);
//                  intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                  mcontext.startActivity(intent);
                  view.finishActivity();

              }else {
                  Toast.makeText(mcontext, "Il Token Fady", Toast.LENGTH_LONG).show();

              }
          }

          @Override
          public void onFailure(Call<TockenReturn> call, Throwable t) {
              Toast.makeText(mcontext , "error", Toast.LENGTH_LONG).show();
          }
      });
    }
}
