package com.example.omnia.ta3ala_2ma_2a2olk_client.Interfaces;

import android.app.Activity;
import android.content.Context;

import com.example.omnia.ta3ala_2ma_2a2olk_client.model.NewsFeed;

import java.util.List;

public interface NewsFeeds {
    interface view {

        void setAdapter(List<NewsFeed> data);
    }
    interface presenter{
       void loadNewsFeeds(Context mContext , Activity activity);
    }
}
