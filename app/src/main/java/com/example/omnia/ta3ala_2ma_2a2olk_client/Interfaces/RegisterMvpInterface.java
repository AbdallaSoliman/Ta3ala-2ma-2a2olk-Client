package com.example.omnia.ta3ala_2ma_2a2olk_client.Interfaces;

import android.content.Context;

public interface RegisterMvpInterface {

    interface view {
        void registerStatus(int key);

    }


    interface presenter{
        int checkInput(String username , String email, String password, String first, String last);
        void loadDataFromServer(String username , String email, String password, String first, String last, String gender, Context mcontext);

    }


}
