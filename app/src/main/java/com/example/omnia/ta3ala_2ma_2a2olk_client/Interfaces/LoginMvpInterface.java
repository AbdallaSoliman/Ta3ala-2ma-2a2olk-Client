package com.example.omnia.ta3ala_2ma_2a2olk_client.Interfaces;

import android.content.Context;

import com.example.omnia.ta3ala_2ma_2a2olk_client.model.User;


public interface LoginMvpInterface {

    interface view {
        void registerStatus(int key);
        void questionActivity(String email);
        void setCustomerId(String id);

    }


   interface presenter {
        int checkInput(String email, String password);
        void loadDataFromServer(String username, String password, Context mcontext);
        void getCustomerId(Context context , String name);
   }
}