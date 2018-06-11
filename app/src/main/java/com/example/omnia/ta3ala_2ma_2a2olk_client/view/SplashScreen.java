package com.example.omnia.ta3ala_2ma_2a2olk_client.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.omnia.ta3ala_2ma_2a2olk_client.Interfaces.SplashInterface;
import com.example.omnia.ta3ala_2ma_2a2olk_client.R;
import com.example.omnia.ta3ala_2ma_2a2olk_client.SharredPreference.SharredPreferenceManager;
import com.example.omnia.ta3ala_2ma_2a2olk_client.presenter.SplashPresenter;

public class SplashScreen extends AppCompatActivity implements SplashInterface.view {
    SplashPresenter presenter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        presenter = new SplashPresenter(this);
        presenter.loadTockenFromServer(getApplicationContext());
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void checksharredpreference() {
//        SharedPreferences userDetails = getSharedPreferences("LoginPref", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = userDetails.edit();
//        SharredPreferenceManager manager = new SharredPreferenceManager(getApplicationContext());
//        String email = manager.getString(userDetails, "email", "null");
//        Toast.makeText(getApplicationContext(), "email is "+email, Toast.LENGTH_LONG).show();
//        if (email.equals("null")){
//            Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
//        }else {
//            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);

//abdalla start
        if(true){
            Intent intent = new Intent(SplashScreen.this, SliderMainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else {
            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
//abdalla end

       // }
    }
}
