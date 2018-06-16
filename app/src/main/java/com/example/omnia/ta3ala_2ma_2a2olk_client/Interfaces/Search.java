package com.example.omnia.ta3ala_2ma_2a2olk_client.Interfaces;

import android.app.Activity;
import android.content.Context;

import com.example.omnia.ta3ala_2ma_2a2olk_client.model.NewsFeed;

import java.util.List;

public interface Search {
    interface view {
        void showSearchResults(List<NewsFeed> data);
    }

    interface presenter {
        void loadSearchResults(String query , Context context , Activity activity);
    }

}
