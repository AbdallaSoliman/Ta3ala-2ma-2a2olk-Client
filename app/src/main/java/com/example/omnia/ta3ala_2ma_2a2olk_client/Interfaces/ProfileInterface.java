package com.example.omnia.ta3ala_2ma_2a2olk_client.Interfaces;

import android.content.Context;

import com.example.omnia.ta3ala_2ma_2a2olk_client.model.SpecialUser;

import java.util.HashMap;
import java.util.List;

public interface ProfileInterface {
    interface view {
        void setPlaces(HashMap<String, List<String>> placesMap);
        void setNames(String firstName , String lastName , String userName);
        void setPlace(String newPlace);
        void setSpecialUser(SpecialUser specialUser);
    }

    interface presenter {
        void updateUsername(Context mContext ,String email, String username, String firstname, String lastname, String gender ,int id ,String image , String password);
        void addLocation(Context mContext , String country , String district , int id);
        void getMyLocations(Context context);
        void getSpecialUser(Context context);
    }
}
