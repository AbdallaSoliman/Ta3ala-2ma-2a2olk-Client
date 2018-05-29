package com.example.omnia.ta3ala_2ma_2a2olk_client.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import com.example.omnia.ta3ala_2ma_2a2olk_client.Interfaces.SplashInterface;
import com.example.omnia.ta3ala_2ma_2a2olk_client.R;
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
}
