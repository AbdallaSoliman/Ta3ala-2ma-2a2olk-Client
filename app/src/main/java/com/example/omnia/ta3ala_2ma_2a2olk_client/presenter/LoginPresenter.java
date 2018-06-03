package com.example.omnia.ta3ala_2ma_2a2olk_client.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.icu.text.UnicodeSetSpanner;
import android.util.Log;
import android.widget.Toast;

import com.example.omnia.ta3ala_2ma_2a2olk_client.Interfaces.LoginMvpInterface;
import com.example.omnia.ta3ala_2ma_2a2olk_client.SharredPreference.SharredPreferenceManager;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Tocken;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.TockenReturn;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.User;
import com.example.omnia.ta3ala_2ma_2a2olk_client.rest.APIService;
import com.example.omnia.ta3ala_2ma_2a2olk_client.rest.ApiClient;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class LoginPresenter implements LoginMvpInterface.presenter {
    LoginMvpInterface.view view;
    private APIService apiInterface;
    private static Pattern emailNamePtrn = Pattern.compile(
            "^[\\\\w\\\\.-]+@([\\\\w\\\\-]+\\\\.)+[A-Z]{2,4}$");
    ArrayList<User> userData;
    int personId;
    String image;
    String first;
    String last;
    String password1;
    String email1;
    String type;
    String gender;
    Boolean enabled;
    String customerService;
    String taaUser;
    User myuser;
    User myuser2;


    public LoginPresenter(LoginMvpInterface.view view) {
        this.view = view;

    }

    @Override
    public int checkInput(String email, String password) {

//        Boolean status = validateEmail(email);
//        Log.i("tag", status.toString());
//        if (!status) {
//            Log.i("tag2", status.toString());
//            view.registerStatus(0);
//            return 0;
//        } else
        if (password.length() < 6) {
            view.registerStatus(1);
            return 0;
        } else if (email.isEmpty() || password.isEmpty()) {
            view.registerStatus(2);
            return 0;
        }
        return 1;
    }

    @Override
    public void loadDataFromServer(String username, String password, final Context mcontext) {
        myuser2 = new User(password ,username);
        Log.e("myuser2name", password);
        Log.e("myuser2Password",username);
        Toast.makeText(mcontext, "logging in", Toast.LENGTH_LONG).show();
        SharedPreferences tokenDetails = mcontext.getSharedPreferences("PersonToken", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = tokenDetails.edit();
        SharredPreferenceManager manager = new SharredPreferenceManager(mcontext);
        String token = manager.getString(tokenDetails, "persontoken", "no");
        Toast.makeText(mcontext, token, Toast.LENGTH_LONG).show();
        apiInterface = ApiClient.getApiClient().create(APIService.class);
        final Tocken user = new Tocken(password, username);
        Call<TockenReturn> call = apiInterface.loginUser("application/json", token, user);
        call.enqueue(new Callback<TockenReturn>() {
            @Override
            public void onResponse(Call<TockenReturn> call, Response<TockenReturn> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(mcontext, "response" + response.body().getTocken().toString(), Toast.LENGTH_LONG).show();
                    SharedPreferences pref = mcontext.getSharedPreferences("PersonToken", 0); // 0 - for private mode
                    SharedPreferences.Editor editor = pref.edit();
                    SharredPreferenceManager m1 = new SharredPreferenceManager(mcontext);
                    String tokenvalue = "Bearer " + response.body().getTocken().toString();
                    m1.setString(pref, "persontoken", tokenvalue);
                    Toast.makeText(mcontext, tokenvalue, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(mcontext, "no response ....", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<TockenReturn> call, Throwable t) {
                Toast.makeText(mcontext, "failed", Toast.LENGTH_LONG).show();
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
    public User loadUser(final Context mcontext , String token , final User user) {
        apiInterface = ApiClient.getApiClient().create(APIService.class);
         Toast.makeText(mcontext , user.getPassword()+"username" , Toast.LENGTH_LONG).show();
         Log.e("username" , user.getPassword());
        Call<User> call = apiInterface.loginUserWithMail("application/json" ,token , user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(mcontext, "success", Toast.LENGTH_LONG).show();
                if (response.isSuccessful()) {
                    myuser = response.body();
                    Log.e("testData", response.body().toString());
                    Log.e("testUser", "user"+user.getEmail());
                    personId = response.body().getPersonId();
                first = response.body().getFirst();
                last = response.body().getLast();
                password1 = response.body().getPassword();
                email1 = response.body().getEmail();
                type = response.body().getType();
                gender = response.body().getGender();
                enabled = response.body().getEnabled();
                customerService = response.body().getCustomerService();
                taaUser = response.body().getTaaUser();
                myuser.setCustomerService(customerService);
                myuser.setEnabled(enabled);
                myuser.setPersonId(personId);
                myuser.setFirst(first);
                myuser.setLast(last);
                myuser.setType(type);
                myuser.setGender(gender);
             //   myuser.setTaaUser(taaUser);
              Log.e("Test","email"+ myuser.getEmail());
                    SharedPreferences pref = mcontext.getSharedPreferences("LoginPref", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("email",myuser.getEmail());
                    Log.e("Hamada",myuser.getEmail());
                    editor.commit();
                    Log.e("userMail",pref.getString("email","MFESH"));
                }else {
                    Toast.makeText(mcontext, "Empty Response", Toast.LENGTH_LONG).show();
                }
                view.questionActivity();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(mcontext, "Login failed ", Toast.LENGTH_LONG).show();
                String message = t.getMessage();
                Log.d("failure", message);
            }

        });
        return myuser;
    }

}
