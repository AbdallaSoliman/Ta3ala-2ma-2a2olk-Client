package com.example.omnia.ta3ala_2ma_2a2olk_client.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;
import com.example.omnia.ta3ala_2ma_2a2olk_client.Interfaces.ProfileInterface;
import com.example.omnia.ta3ala_2ma_2a2olk_client.SharredPreference.SharredPreferenceManager;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.MainCategories;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.ServerResonse;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.SpecialUser;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.SubCategories;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Tauser;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.User;
import com.example.omnia.ta3ala_2ma_2a2olk_client.rest.APIService;
import com.example.omnia.ta3ala_2ma_2a2olk_client.rest.ApiClient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePresenter implements ProfileInterface.presenter{
    ProfileInterface.view view ;
    private APIService apiInterface;
    String tocken = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNTI4NDg4MTkwLCJpYXQiOjE1Mjc4ODMzOTB9.cCXhrzOLyRnaTNcnmMCC70_SPM_RVW2hPnYwryh8VtSUJhuFfwxv5s0jseCPf9iZkCnzAJjHy7ie6LwNz7Ptzg";
    HashMap<String, List<String>>  placesMap;
    List<MainCategories> getCategories;
    List<SubCategories> categories;


    public ProfilePresenter(ProfileInterface.view view) {
        this.view = view;
    }

    @Override
    public void updateUsername(final Context mContext, String email, final String username, final String firstname, final String lastname, String gender, int id, String image , String password) {
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
//                            Toast.makeText(mContext, result + " 2333", Toast.LENGTH_LONG).show();
                            view.setNames(firstname,lastname,username);
                        }
                    }
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ServerResonse> call, Throwable t) {
//                Toast.makeText(mContext,"Error",Toast.LENGTH_LONG).show();
                String message = t.getMessage();
//                Toast.makeText(mContext,"Hesham 111",Toast.LENGTH_LONG).show();
                Log.d("failuress", message);
            }
        });
    }

    @Override
    public void addLocation(final Context mContext, final String city, final String district , int id) {
        SharedPreferences tokenDetails = mContext.getSharedPreferences("PersonToken", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = tokenDetails.edit();
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
//                    Toast.makeText(mContext,"Updated Successful",Toast.LENGTH_LONG).show();
                    Log.i("user response" ,response.body().getMessage().toString()+"");
                    view.setPlace(city+"/"+district);
                }
                else{
//                    Toast.makeText(mContext,"Updated Fail",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ServerResonse> call, Throwable t) {
//                Toast.makeText(mContext,"Hesham 222",Toast.LENGTH_LONG).show();
                Log.i("user response" ,"failed");

            }
        });
    }

    // Hesham Muhammed
    @Override
    public void getMyLocations(final Context mContext) {
        SharedPreferences tokenDetails = mContext.getSharedPreferences("PersonToken", Context.MODE_PRIVATE);
        SharredPreferenceManager manager = new SharredPreferenceManager(mContext);
        String token = manager.getString(tokenDetails, "persontoken", "no");
        apiInterface = ApiClient.getApiClient().create(APIService.class);
        retrofit2.Call<List<MainCategories>> getAllCategories = apiInterface.mainCategories("");
        getAllCategories = apiInterface.mainCategories(token);
        getAllCategories.enqueue(new Callback<List<MainCategories>>() {
            @Override
            public void onResponse(retrofit2.Call<List<MainCategories>> call, Response<List<MainCategories>> response) {
                if (response.body() != null) {
                    getCategories = response.body();
                    if (getCategories.size() > 0) {
                        for (int y = 0; y < 3; y++) {
                            categories = getCategories.get(y).getSubCatCollection();
                            HashMap<String, List<String>> hashMap = new HashMap<>();
                            for (int i = 0; i < categories.size(); i++) {
                                SubCategories subCategory = categories.get(i);
                                if (hashMap.get(subCategory.getSubCatName()) == null) {
                                    hashMap.put(subCategory.getSubCatName(), new ArrayList<String>());
                                    if (subCategory.getDescription() != null) {
                                        hashMap.get(subCategory.getSubCatName()).add(subCategory.getDescription());
                                    }
                                } else {
                                    if (subCategory.getDescription() != null) {
                                        hashMap.get(subCategory.getSubCatName()).add(subCategory.getDescription());
                                    }
                                }
                            }
                            if (y == 1) {
                                placesMap = hashMap;
                            }
                        }
                    }

                    if (response != null) {
                        view.setPlaces(placesMap);
                     }
                }
            }
            @Override
            public void onFailure(retrofit2.Call<List<MainCategories>> call, Throwable t) {

            }
        });
    }

    @Override
    public void getSpecialUser(Context mcontext) {
        SharedPreferences tokenDetails = mcontext.getSharedPreferences("PersonToken", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = tokenDetails.edit();
        SharredPreferenceManager manager = new SharredPreferenceManager(mcontext);
        String token = manager.getString(tokenDetails, "persontoken", "no");
        apiInterface = ApiClient.getApiClient().create(APIService.class);
        SharedPreferences userDetails = mcontext.getSharedPreferences("PersonToken", Context.MODE_PRIVATE);
        userDetails = mcontext.getSharedPreferences("LoginPref", Context.MODE_PRIVATE);
        manager = new SharredPreferenceManager(mcontext);
        String idtemp = manager.getString(userDetails, "id", "no");

        Call<SpecialUser> getSpecial = apiInterface.getSpecial("application/json",token,idtemp);
        getSpecial.enqueue(new Callback<SpecialUser>() {
            @Override
            public void onResponse(Call<SpecialUser> call, Response<SpecialUser> response) {
                if (response != null){
                    view.setSpecialUser(response.body());
                }
            }

            @Override
            public void onFailure(Call<SpecialUser> call, Throwable t) {

            }
        });
    }
}