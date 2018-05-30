package com.example.omnia.ta3ala_2ma_2a2olk_client.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;


import com.example.omnia.ta3ala_2ma_2a2olk_client.Interfaces.RegisterMvpInterface;
import com.example.omnia.ta3ala_2ma_2a2olk_client.SharredPreference.SharredPreferenceManager;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.User;
import com.example.omnia.ta3ala_2ma_2a2olk_client.rest.APIService;
import com.example.omnia.ta3ala_2ma_2a2olk_client.rest.ApiClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class SignupPresenter implements RegisterMvpInterface.presenter {
    RegisterMvpInterface.view view;
    private APIService apiInterface;

    public SignupPresenter(RegisterMvpInterface.view view) {
        this.view = view;

    }


    @Override
    public int checkInput(String username , String email, String password, String first, String last) {
        Boolean status = validateEmail(email);
        Log.i("tag", status.toString());
        if (!status) {
            Log.i("tag2", status.toString());
            view.registerStatus(0);
            return 0;
        } else if (password.length() < 6) {
            view.registerStatus(1);
            return 0;
        } else if (email.isEmpty() || password.isEmpty() || first.isEmpty() || last.isEmpty()|| username.isEmpty()) {
            view.registerStatus(2);
            return 0;
        }
        return 1;
    }

    @Override
    public void loadDataFromServer(String username , String email, String password, String first, String last, String gender, final Context mcontext) {
        Toast.makeText(mcontext, "Signing up", Toast.LENGTH_LONG).show();
        final User user = new User(first, last, password, email, "user", gender, true , username);

        SharedPreferences tokenDetails = mcontext.getSharedPreferences("PersonToken", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = tokenDetails.edit();
        SharredPreferenceManager manager = new SharredPreferenceManager(mcontext);
        String token = manager.getString(tokenDetails, "persontoken", "no");
        Toast.makeText(mcontext, token, Toast.LENGTH_LONG).show();

        apiInterface = ApiClient.getApiClient().create(APIService.class);
        Call<User> call = apiInterface.registerUser("application/json", token, user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(mcontext, "Register succesful ", Toast.LENGTH_LONG).show();
                    Toast.makeText(mcontext, response.body().getEmail(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(mcontext, "Register failed ", Toast.LENGTH_LONG).show();
                String message = t.getMessage();
                Log.d("failure", message);

            }
        });


    }

    public boolean validateEmail(String email) {

        Pattern pattern;
        Matcher matcher;
        Log.i("tag", email);
        String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();

    }
}
