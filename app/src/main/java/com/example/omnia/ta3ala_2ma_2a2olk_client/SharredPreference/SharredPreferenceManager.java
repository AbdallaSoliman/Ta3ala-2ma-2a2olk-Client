package com.example.omnia.ta3ala_2ma_2a2olk_client.SharredPreference;

import android.content.Context;
import android.content.SharedPreferences;

public class SharredPreferenceManager {
    Context mContext;

    public SharredPreferenceManager(Context context){
        mContext = context;
    }

    public SharedPreferences getSharedPreferences(String key){
        return mContext.getSharedPreferences(key, mContext.MODE_PRIVATE);
    }

    public void setString(SharedPreferences prefs, String key, String value){
        prefs.edit().putString(key, value).commit();
    }

    public String getString(SharedPreferences prefs, String key, String defaultValue){
        return prefs.getString(key, defaultValue);
    }


}
