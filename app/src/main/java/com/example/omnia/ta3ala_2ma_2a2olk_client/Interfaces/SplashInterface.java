package com.example.omnia.ta3ala_2ma_2a2olk_client.Interfaces;

import android.content.Context;

import com.example.omnia.ta3ala_2ma_2a2olk_client.model.Tocken;

public interface SplashInterface {

    interface view{

        void finishActivity();
        void checksharredpreference();
     }

     interface presenter {
         void loadTockenFromServer(Context mcontext);
     }

}
