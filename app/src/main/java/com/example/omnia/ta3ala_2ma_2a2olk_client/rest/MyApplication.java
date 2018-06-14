package com.example.omnia.ta3ala_2ma_2a2olk_client.rest;

import android.app.Application;
import android.content.Context;

/**
 * Created by HeshamMuhammed on 6/14/2018.
 */

public class MyApplication extends Application {
    private static Application sApplication;

    public static Application getApplication() {
        return sApplication;
    }

    public static Context getContext() {
        return getApplication().getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }
}