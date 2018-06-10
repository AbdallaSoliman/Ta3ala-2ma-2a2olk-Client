package com.example.omnia.ta3ala_2ma_2a2olk_client.Interfaces;

import android.content.Context;

public interface ProfileInterface {
    interface view {

    }

    interface presenter {
        void updateUsername(Context mContext ,String email, String username, String firstname, String lastname, String gender ,int id ,String image , String password);
        void addLocation(Context mContext , String country , String district , int id);
    }


}
