package com.example.omnia.ta3ala_2ma_2a2olk_client.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.telecom.Call;
import android.util.Log;
import android.widget.Toast;

import com.example.omnia.ta3ala_2ma_2a2olk_client.Interfaces.ProfileInterface;
import com.example.omnia.ta3ala_2ma_2a2olk_client.SharredPreference.SharredPreferenceManager;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.ServerResonse;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Tauser;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.TockenReturn;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.User;
import com.example.omnia.ta3ala_2ma_2a2olk_client.rest.APIService;
import com.example.omnia.ta3ala_2ma_2a2olk_client.rest.ApiClient;

import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePresenter implements ProfileInterface.presenter{
    ProfileInterface.view view ;
    private APIService apiInterface;
    String tocken = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNTI4NDg4MTkwLCJpYXQiOjE1Mjc4ODMzOTB9.cCXhrzOLyRnaTNcnmMCC70_SPM_RVW2hPnYwryh8VtSUJhuFfwxv5s0jseCPf9iZkCnzAJjHy7ie6LwNz7Ptzg";



    public ProfilePresenter(ProfileInterface.view view) {
        this.view = view;
    }


    @Override
    public void updateUsername(final Context mContext, String email, String username, String firstname, String lastname, String gender, int id, String image , String password) {
        SharedPreferences tokenDetails = mContext.getSharedPreferences("PersonToken", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = tokenDetails.edit();
        SharredPreferenceManager manager = new SharredPreferenceManager(mContext.getApplicationContext());
        String token = manager.getString(tokenDetails, "persontoken", "no");
        Log.e("subcat", "sub" + token);
        User user = new User(id , image , firstname, lastname , password ,email , "user",gender , true,username );
        apiInterface = ApiClient.getApiClient().create(APIService.class);
        retrofit2.Call<ServerResonse> call = apiInterface.updateUser("application/json", token, user);
        call.enqueue(new Callback<ServerResonse>() {
            @Override
            public void onResponse(retrofit2.Call<ServerResonse> call, Response<ServerResonse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        String result = response.body().getMessage().toString();
                        if (result.length() > 0) {
                            Toast.makeText(mContext, result + "", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ServerResonse> call, Throwable t) {
                Toast.makeText(mContext,"Error",Toast.LENGTH_LONG).show();
                String message = t.getMessage();
                Log.d("failuress", message);
            }
        });
    }

    @Override
    public void addLocation(final Context mContext, String city, String district , int id) {
        SharedPreferences tokenDetails = mContext.getSharedPreferences("PersonToken", Context.MODE_PRIVATE);
        SharredPreferenceManager manager = new SharredPreferenceManager(mContext);
        String token = manager.getString(tokenDetails, "persontoken", "no");
        apiInterface = ApiClient.getApiClient().create(APIService.class);
        Tauser tauser = new Tauser();
        tauser.setPersonId(id);
        tauser.setCity(city);
        tauser.setDistrict(district);
        Log.i("il-id",+id+"");
        retrofit2.Call<ServerResonse> call = apiInterface.addTaUserdata("application/json",token,tauser);
        call.enqueue(new Callback<ServerResonse>() {
            @Override
            public void onResponse(retrofit2.Call<ServerResonse> call, Response<ServerResonse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(mContext,"Updated Successful",Toast.LENGTH_LONG).show();
                    Log.i("user response" ,response.body().getMessage().toString()+"");
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ServerResonse> call, Throwable t) {
                Log.i("user response" ,"failed");

            }
        });
    }
}
