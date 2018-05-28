package com.example.omnia.ta3ala_2ma_2a2olk_client.Interfaces;

import android.content.Context;

import com.example.omnia.ta3ala_2ma_2a2olk_client.model.User;


public interface LoginMvpInterface {

    interface view {
        void registerStatus(int key);

    }


   interface presenter {
        int checkInput(String email, String password);
        User loadDataFromServer(String email, String password, Context mcontext);
   }



}
